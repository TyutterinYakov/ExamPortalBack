package ru.pet.portal.api.service;

import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.service.model.ExamResult;

import java.util.List;
import java.util.UUID;

public interface ExamResultService {
    ExamResult submitExam(UUID id, List<AnswerExamRequestDto> answers);
}
