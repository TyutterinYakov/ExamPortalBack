package ru.pet.portal.api.service;

import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.dto.gigachat.QuestionGenerationRequest;
import ru.pet.portal.store.entity.QuizE;

import java.util.Set;
import java.util.UUID;

public interface GigaChatService {
    GeneratedQuiz generateQuiz(QuestionGenerationRequest request);

    Set<UUID> getRecommendations(Set<String> interests, Set<Object[]> quizWithNameAndId);
}