package ru.pet.portal.api.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class QuizSpecification {
    private UUID quizId;
    private long maxMarks;
    private long time;
    private long countOfQuestion;
}
