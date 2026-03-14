package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pet.portal.api.controller.dto.quiz.QuizRequestDto;
import ru.pet.portal.api.controller.dto.quiz.QuizResponseDto;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.api.service.dto.gigachat.QuestionGenerationRequest;
import ru.pet.portal.store.entity.QuizE;

@Mapper(componentModel = "Spring", uses = {CategoryMapper.class})
public interface QuizMapper {
    @Mapping(target = "active", source = "active")
    QuizE toEntity(GeneratedQuiz rq, boolean active);
    QuizE toEntity(QuizRequestDto quizRequestDto);
    QuizResponseDto toDto(QuizE quiz);
}
