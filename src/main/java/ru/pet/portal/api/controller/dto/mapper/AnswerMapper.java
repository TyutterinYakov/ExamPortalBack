package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.controller.dto.answer.AnswerRequestDto;
import ru.pet.portal.api.controller.dto.answer.AnswerResponseDto;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.store.entity.Answer;
import ru.pet.portal.store.entity.AnswerE;
import ru.pet.portal.store.entity.QuestionE;

@Mapper(componentModel = "Spring")
public interface AnswerMapper {
    AnswerE toEntity(AnswerRequestDto answerRequestDto);
    AnswerRequestDto toDto(Answer answer);
    AnswerResponseDto toResponseDto(Answer answer);
    @Mapping(target = "question", source = "question")
    AnswerE toEntity(GeneratedQuiz.GeneratedQuestion.Answer answer, QuestionE question);

}
