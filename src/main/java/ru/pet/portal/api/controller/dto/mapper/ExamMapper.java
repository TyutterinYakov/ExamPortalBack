package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultDto;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;

@Mapper(componentModel = "Spring")
public interface ExamMapper {
    ExamResultDto toDto(ExamResult examResult);
    AnswerExamRequest toModel(AnswerExamRequestDto answerExamRequestDto);
}
