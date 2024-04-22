package ru.pet.portal.api.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ExamResult {

    protected Long answerId;
	protected int validQuestion;
	protected int invalidQuestion;
	protected int skipQuestion;
	protected int countPoints;
	protected String quizTitle;
}
