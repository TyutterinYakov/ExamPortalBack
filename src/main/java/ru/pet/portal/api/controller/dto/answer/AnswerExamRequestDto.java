package ru.pet.portal.api.controller.dto.answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerExamRequestDto {
    private String content;
    private String answer;
}
