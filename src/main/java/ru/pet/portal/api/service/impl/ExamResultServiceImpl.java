package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.api.service.model.ExamResult;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

//    private final
    @Override
    public ExamResult submitExam(UUID id, List<AnswerExamRequestDto> answers) {
        return null;
    }
}
