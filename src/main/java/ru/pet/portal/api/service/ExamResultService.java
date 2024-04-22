package ru.pet.portal.api.service;

import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;

import java.util.List;
import java.util.UUID;

public interface ExamResultService {
    ExamResult submitExam(UUID quizId, UUID userId, List<AnswerExamRequest> answers);
}
