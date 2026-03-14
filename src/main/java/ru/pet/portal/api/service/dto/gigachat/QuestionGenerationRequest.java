package ru.pet.portal.api.service.dto.gigachat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGenerationRequest {
    private String topic;
    private String difficulty;
    private int questionCount;
    private String format; // "multiple-choice", "true-false", "short-answer"
}