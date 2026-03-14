package ru.pet.portal.api.service;

import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.dto.gigachat.QuestionGenerationRequest;

public interface GigaChatService {
    GeneratedQuiz generateQuiz(QuestionGenerationRequest request);
}