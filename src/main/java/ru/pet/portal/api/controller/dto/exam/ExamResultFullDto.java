package ru.pet.portal.api.controller.dto.exam;

import lombok.Getter;
import lombok.Setter;
import ru.pet.portal.api.controller.dto.answer.ExamAnswerDto;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ExamResultFullDto {
    private UUID id;
    private int validQuestion;
    private int invalidQuestion;
    private int skipQuestion;
    private int countPoints;
    private int maxMarks;
    private String quizTitle;
    private String categoryTitle;
    private List<ExamAnswerDto> examAnswers;
    private UserResponseDto user;
}
