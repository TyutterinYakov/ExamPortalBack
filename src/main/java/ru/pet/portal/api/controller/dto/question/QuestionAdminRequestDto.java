package ru.pet.portal.api.controller.dto.question;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.pet.portal.api.controller.dto.answer.AnswerRequestDto;
import ru.pet.portal.api.controller.dto.validation.group.CheckExistsCorrectlyAnswer;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class QuestionAdminRequestDto {
    @NotBlank
    @Size(max = 4000, min = 5)
    private String content;
    @NotNull
    private UUID quizId;
    @CheckExistsCorrectlyAnswer
    private Set<@NotNull @Valid AnswerRequestDto> answers;
    @Positive
    private int marks;
    @Positive
    private int time;
}
