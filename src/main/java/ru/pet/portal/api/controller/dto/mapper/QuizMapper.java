package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.quiz.QuizRequestDto;
import ru.pet.portal.api.controller.dto.quiz.QuizResponseDto;
import ru.pet.portal.store.entity.Quiz;

@Mapper(componentModel = "Spring", uses = {CategoryMapper.class})
public interface QuizMapper {

    Quiz toEntity(QuizRequestDto quizRequestDto);
    QuizResponseDto toDto(Quiz quiz);
}
