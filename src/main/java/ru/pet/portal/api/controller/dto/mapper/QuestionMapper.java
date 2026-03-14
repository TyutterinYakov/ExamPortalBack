package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pet.portal.api.controller.dto.question.QuestionAdminRequestDto;
import ru.pet.portal.api.controller.dto.question.QuestionAdminResponseDto;
import ru.pet.portal.api.controller.dto.question.QuestionResponseDto;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.store.entity.QuestionE;
import ru.pet.portal.store.entity.QuizE;

import java.util.UUID;

@Mapper(componentModel = "Spring", uses = {QuizMapper.class, AnswerMapper.class})
public interface QuestionMapper {

    QuestionResponseDto toDto(QuestionE questionE);
    QuestionAdminResponseDto toAdminDto(QuestionE questionE);
    QuestionE toEntity(QuestionAdminRequestDto questionRequestDto);

    @Mapping(target = "quiz", source = "quiz")
    @Mapping(target = "time", source = "question.time")
    QuestionE toEntity(GeneratedQuiz.GeneratedQuestion question, QuizE quiz);
}
