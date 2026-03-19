package ru.pet.portal.api.service.impl;

import chat.giga.model.completion.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import ru.pet.portal.api.config.GigaChatConfig;
import ru.pet.portal.api.service.GigaChatService;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.dto.gigachat.QuestionGenerationRequest;
import ru.pet.portal.api.util.JsonUtil;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("gigachat")
public class GigaChatServiceImpl implements GigaChatService {

    private final RestTemplate restTemplate;
    private final GigaChatConfig gigaChatConfig;
    private final AccessKeyOrchestrator accessKeyOrchestrator;

    @Override
    @SneakyThrows
    public GeneratedQuiz generateQuiz(QuestionGenerationRequest request) {
        try {
            // Создаем промпт для генерации теста
            String prompt = createPrompt(request);

            ResponseEntity<CompletionResponse> response = getCompletionResponseResponse(prompt);
            assert response.getBody() != null;
            return parseResponse(response.getBody().choices().getFirst().message().content());
        } catch (Exception e) {
            log.error("Error generating quiz with GigaChat", e);
            throw new RuntimeException("Failed to generate quiz", e);
        }
    }

    @Override
    public Set<UUID> getRecommendations(Set<String> interests, Set<Object[]> quizWithIdAndName) {
        if (CollectionUtils.isEmpty(interests) || CollectionUtils.isEmpty(quizWithIdAndName)) {
            log.info("Interests or quizes is empty. Skip recommendations");
            return Set.of();
        }
        final String prompt = createPromptForRecommendation(interests,
                quizWithIdAndName.stream().map(idWithTitle -> (String) idWithTitle[1]).collect(Collectors.toSet()));

        final ResponseEntity<CompletionResponse> completionResponse = getCompletionResponseResponse(prompt);
        assert completionResponse.getBody() != null;
        final String content = completionResponse.getBody().choices().getFirst().message().content();

        if ("Совпадения не найдены".equalsIgnoreCase(content)) {
            log.info("No matches found for interests: {}", interests);
            return Set.of();
        }

        final Map<String, UUID> quizIdByTitle = new HashMap<>();
        //Известно, что названия тестов могут повторяться, поэтому в результате возьмем последний встретившийся в списке
        quizWithIdAndName.forEach(q -> quizIdByTitle.put(((String) q[1]).toLowerCase(), (UUID) q[0]));

        final Set<UUID> result = new HashSet<>();
        Arrays.stream(content.split("\n")).forEach(
                quizTitleWithInterests -> {
                    final String[] split = quizTitleWithInterests.split("\\|\\|\\|");
                    if (split.length != 2) {
                        log.debug("Skip line: {}. Wrong format", quizTitleWithInterests);
                        return;
                    }
                    log.info("Quiz: {}, interests: {}", split[0], split[1]);
                    final UUID quizId = quizIdByTitle.get(split[0].toLowerCase().trim());
                    if (quizId != null) {
                        result.add(quizId);
                    }
                }
        );
        return result;
    }

    private String createPromptForRecommendation(Set<String> interests, Set<String> quizNames) {
        return String.format("""
                        Тесты на какие темы можно предложить пользователю?
                        Интересы пользователя: %s.
                        Доступные тесты:  %s.
                        Результаты выведи как: Название теста ||| Интерес(Если их несколько то, через запятую).
                        Если ни один тест не совпадает с интересами пользователя - верни фразу "Совпадения не найдены"
                        и больше ничего, иначе получишь ШТРАФ!
                        """,
                interests.stream().map(i -> String.format("\"%s\"", i)).collect(Collectors.joining(", ")),
                quizNames.stream().map(i -> String.format("\"%s\"", i)).collect(Collectors.joining(", ")));
    }

    private String createPrompt(QuestionGenerationRequest request) {
        return String.format(
                """
                        Создай тест по теме '%s' уровня сложности '%s' с %d вопросами.
                        На каждый вопрос должен быть только один правильный ответ
                        Формат вопросов: %s. Для каждого вопроса укажи:
                            - содержание вопроса
                            - список ответов (МАКСИМУМ 4 варианта, МИНИМУМ 2. При этом правильный ответ должен быть только один)
                            - количество баллов за вопрос (от 1 до 5)
                            - время на ответ (в секундах, от 30 до 120)
                        Верни ответ в формате JSON, соответствующем следующей структуре:
                        {
                            "title": "Название теста по %s",
                            "description": "Описание теста по %s",
                            "questions": [
                                {
                                    "content": "Вопрос 1",
                                    "answers": [
                                        {"reply": "Ответ 1", "correctly": false},
                                        {"reply": "Ответ 2", "correctly": true},
                                        {"reply": "Ответ 3", "correctly": false},
                                        {"reply": "Ответ 4", "correctly": false}
                                    ]
                                }
                            ],
                            "marks": 3,
                            "time": 60
                        }
                        """,
                request.getTopic(),
                request.getDifficulty(),
                request.getQuestionCount(),
                getRussianFormat(request.getFormat()),
                request.getTopic(),
                request.getTopic()
        );
    }

    private String getRussianFormat(String format) {
        return switch ((String.valueOf(format)).toLowerCase()) {
            case "multiple-choice" -> "с несколькими вариантами ответов";
            case "true-false" -> "на определение верности утверждения";
            default -> "с кратким ответом";
        };
    }

    protected static GeneratedQuiz parseResponse(String responseBody) {
        try {
            // Извлекаем JSON из ответа (GigaChat может возвращать дополнительный текст)
            String jsonPart = extractJsonFromResponse(responseBody);

            // Парсим JSON в объект
            GeneratedQuiz quiz = JsonUtil.fromJson(jsonPart, GeneratedQuiz.class);

            // Валидируем ответ
            if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
                throw new IllegalStateException("No questions generated");
            }

            return quiz;
        } catch (Exception e) {
            log.error("Error parsing GigaChat response: " + responseBody, e);
            throw new RuntimeException("Failed to parse generated quiz", e);
        }
    }

    private static String extractJsonFromResponse(String response) {
        int start = response.indexOf('{');
        int end = response.lastIndexOf('}');
        if (start != -1 && end != -1 && end > start) {
            return response.substring(start, end + 1);
        }
        throw new IllegalStateException("No JSON found in response");
    }

    @NotNull
    private ResponseEntity<CompletionResponse> getCompletionResponseResponse(String prompt) {
        final CompletionRequest gigaChatRq = CompletionRequest.builder()
                .model(gigaChatConfig.getModel())
                .temperature(0.7f)
                .maxTokens(2000)
                .stream(false)
                .messages(Collections.singletonList(
                        ChatMessage.builder().role(ChatMessageRole.USER).content(prompt).build()))
                .build();

        // Затем отправляем запрос на генерацию теста с полученным токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessKeyOrchestrator.getAccessToken());
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        HttpEntity<CompletionRequest> entity = new HttpEntity<>(gigaChatRq, headers);

        return restTemplate.exchange(
                gigaChatConfig.getApiUrl(),
                HttpMethod.POST,
                entity,
                CompletionResponse.class
        );
    }

    @Component
    @RequiredArgsConstructor
    private static class AccessKeyOrchestrator {
        private final RestTemplate restTemplate;
        private final GigaChatConfig gigaChatConfig;
        /**
         * Время получения токена для запроса в гигачат (живет 30 мин, судя по документации)
         */
        private OffsetDateTime timeOfReceipt;
        /**
         * Значения ключа для отправки в гигачат
         */
        private String accessKey;

        @SneakyThrows
        public synchronized String getAccessToken() {
            if (timeOfReceipt != null && timeOfReceipt.isAfter(OffsetDateTime.now().minusMinutes(30))) {
                return accessKey;
            }
            // Устанавливаем заголовки
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + gigaChatConfig.getApiKey());
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("RqUID", UUID.randomUUID().toString());
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>("scope=GIGACHAT_API_PERS", headers);
            try {
                final String key = (String) new ObjectMapper().readValue(restTemplate.exchange(
                                gigaChatConfig.getAuthApiUrl(), HttpMethod.POST, entity, String.class).getBody(),
                        new TypeReference<Map<String, Object>>() {
                        }).get("access_token");
                timeOfReceipt = OffsetDateTime.now(); //TODO: брать expires_at
                accessKey = key;
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                throw ex;
            }
            return accessKey;
        }
    }
}