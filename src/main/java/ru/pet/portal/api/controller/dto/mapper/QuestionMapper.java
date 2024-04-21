package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.question.QuestionAdminRequestDto;
import ru.pet.portal.api.controller.dto.question.QuestionAdminResponseDto;
import ru.pet.portal.api.controller.dto.question.QuestionResponseDto;
import ru.pet.portal.store.entity.Question;

@Mapper(componentModel = "Spring", uses = {QuizMapper.class})
public interface QuestionMapper {

    QuestionResponseDto toDto(Question question);
    QuestionAdminResponseDto toAdminDto(Question question);
    Question toEntity(QuestionAdminRequestDto questionRequestDto);
}
