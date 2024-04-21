package ru.pet.portal.api.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ExamResult {

    private Long answerId;
	private int validQuestion;
	private int invalidQuestion;
	private int skipQuestion;
	private int countPoints;
    private String quizTitle;
}
