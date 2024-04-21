package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.answer.AnswerRequestDto;
import ru.pet.portal.api.controller.dto.answer.AnswerResponseDto;
import ru.pet.portal.store.entity.Answer;

@Mapper(componentModel = "Spring")
public interface AnswerMapper {
    Answer toEntity(AnswerRequestDto answerRequestDto);
    AnswerRequestDto toDto(Answer answer);
    AnswerResponseDto toResponseDto(Answer answer);

}
