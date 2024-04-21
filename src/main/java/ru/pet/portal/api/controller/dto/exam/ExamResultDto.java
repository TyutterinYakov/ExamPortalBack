package ru.pet.portal.api.controller.dto.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ExamResultDto {
    private int validQuestion;
    private int invalidQuestion;
    private int skipQuestion;
    private int countPoints;
}
