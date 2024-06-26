package ru.pet.portal.api.controller.dto.answer;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerExamRequestDto {
    private UUID questionId;
    private String givenAnswer;
}
