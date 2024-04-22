package ru.pet.portal.api.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;
import ru.pet.portal.store.entity.Answer;
import ru.pet.portal.store.entity.Question;
import ru.pet.portal.store.repository.QuestionRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final QuestionRepository questionRepository;

    @Override
    public ExamResult submitExam(UUID quizId, UUID userId, List<AnswerExamRequest> answers) {
        final Map<UUID, Pair<Answer, Integer>> answerCorrectlyByQuestionId = questionRepository.getByQuizIdAndActiveQuiz(quizId, true).stream()
                .collect(Collectors.toMap(Question::getId, q -> new ImmutablePair<>(
                        q.getAnswers().stream().filter(Answer::isCorrectly)
                        .findFirst().orElseThrow(), q.getMarks())));

        ExamResult examResult = new ExamResult();

        answers.forEach(a -> {
            //Проверка, что на вопрос был дан ответ
            if (StringUtils.isBlank(a.getGivenAnswer())) {
                examResult.setSkipQuestion(examResult.getSkipQuestion() + 1);
                return;
            }
            Pair<Answer, Integer> answerAndMarks = answerCorrectlyByQuestionId.get(a.getQuestionId());
            //Проверка, что на вопрос был дан правильный ответ
            if (Objects.equals(a.getGivenAnswer(), answerAndMarks.getLeft().getReply())) {
                examResult.setValidQuestion(examResult.getValidQuestion() + 1);
                examResult.setCountPoints(examResult.getCountPoints() + answerAndMarks.getRight());
            } else {
                examResult.setInvalidQuestion(examResult.getInvalidQuestion() + 1);
            }
        });
        return examResult;
    }
}
