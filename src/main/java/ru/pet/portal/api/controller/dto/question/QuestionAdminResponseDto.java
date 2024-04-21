package ru.pet.portal.api.controller.dto.question;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.pet.portal.api.controller.dto.answer.AnswerRequestDto;
import ru.pet.portal.api.controller.dto.quiz.QuizResponseDto;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
public class QuestionAdminResponseDto {
    private UUID id;
    private String content;
    private QuizResponseDto quiz;
    private Set<AnswerRequestDto> answers;
    private int marks;
    private int time;
}
