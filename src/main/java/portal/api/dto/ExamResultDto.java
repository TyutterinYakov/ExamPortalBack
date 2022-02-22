package portal.api.dto;

import java.util.Map;

import portal.store.entity.QuizeEntity;
import portal.store.entity.UserEntity;

public class ExamResultDto {

	private Long answerId;
	private int validQuestion;
	private int invalidQuestion;
	private int skipQuestion;
	private int countPoints;
    private QuizeDto quizeDto;
    private Map<String, Map<String, String>> questionsAndGivenAnswer;
    
    
	public ExamResultDto() {
		super();
	}
	public ExamResultDto(Long answerId, int validQuestion, int invalidQuestion, int skipQuestion, int countPoints,
			QuizeDto quizeDto, Map<String, Map<String, String>> questionsAndGivenAnswer) {
		super();
		this.answerId = answerId;
		this.validQuestion = validQuestion;
		this.invalidQuestion = invalidQuestion;
		this.skipQuestion = skipQuestion;
		this.countPoints = countPoints;
		this.quizeDto = quizeDto;
		this.questionsAndGivenAnswer = questionsAndGivenAnswer;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public int getValidQuestion() {
		return validQuestion;
	}
	public void setValidQuestion(int validQuestion) {
		this.validQuestion = validQuestion;
	}
	public int getInvalidQuestion() {
		return invalidQuestion;
	}
	public void setInvalidQuestion(int invalidQuestion) {
		this.invalidQuestion = invalidQuestion;
	}
	public int getSkipQuestion() {
		return skipQuestion;
	}
	public void setSkipQuestion(int skipQuestion) {
		this.skipQuestion = skipQuestion;
	}
	public int getCountPoints() {
		return countPoints;
	}
	public void setCountPoints(int countPoints) {
		this.countPoints = countPoints;
	}
	public QuizeDto getQuizeDto() {
		return quizeDto;
	}
	public void setQuizeDto(QuizeDto quizeDto) {
		this.quizeDto = quizeDto;
	}
	public Map<String, Map<String, String>> getQuestionsAndGivenAnswer() {
		return questionsAndGivenAnswer;
	}
	public void setQuestionsAndGivenAnswer(Map<String, Map<String, String>> questionsAndGivenAnswer) {
		this.questionsAndGivenAnswer = questionsAndGivenAnswer;
	}
    
    
}
