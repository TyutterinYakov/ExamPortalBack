package ru.pet.portal.api.service.dto.gigachat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pet.portal.api.controller.dto.validation.group.Create;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGenerationRequest {
    @NotBlank
    private String topic;
    @NotBlank
    private String difficulty;
    @Max(4)
    @Min(2)
    @NotNull
    private Integer questionCount;
    private String format; // "multiple-choice", "true-false", "short-answer"
    @NotNull
    private UUID categoryId;
}