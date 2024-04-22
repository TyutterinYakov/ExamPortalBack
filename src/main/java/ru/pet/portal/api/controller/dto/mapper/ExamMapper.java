package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultFullDto;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;
import ru.pet.portal.store.entity.ExamResultE;

@Mapper(componentModel = "Spring", uses = UserMapper.class)
public interface ExamMapper {
    ExamResultDto toDto(ExamResult examResult);
    AnswerExamRequest toModel(AnswerExamRequestDto answerExamRequestDto);

    @Mapping(source = "quiz.title", target = "quizTitle")
    @Mapping(source = "quiz.category.title", target = "categoryTitle")
    ExamResultFullDto toFullDto(ExamResultE examResultFull);
}
