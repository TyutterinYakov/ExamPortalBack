package ru.pet.portal.api.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerExamRequest {
    private UUID questionId;
    private String givenAnswer;
}
