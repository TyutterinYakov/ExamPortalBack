package ru.pet.portal.api.controller.dto.answer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExamAnswerDto {
    private String givenAnswer;
    private String answer;
    private String questionContent;
}
