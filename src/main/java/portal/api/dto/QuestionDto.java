package portal.api.dto;

import javax.validation.constraints.NotEmpty;

public class QuestionDto {

	private Long questionId;
	@NotEmpty
	private String content;
	private String image;
	private String givenAnswer;
	@NotEmpty
	private String option1;
	@NotEmpty
	private String option2;
	private String option3;
	private String option4;
	@NotEmpty
	private QuizeDto quizeDto;
	
	public QuestionDto() {
		super();
	}
	public QuestionDto(Long questionId, @NotEmpty String content, @NotEmpty String option1,
			@NotEmpty String option2, String option3, String option4, @NotEmpty QuizeDto quizeDto) {
		super();
		this.questionId = questionId;
		this.content = content;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.quizeDto = quizeDto;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGivenAnswer() {
		return givenAnswer;
	}
	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public QuizeDto getQuizeDto() {
		return quizeDto;
	}
	public void setQuizeDto(QuizeDto quizeDto) {
		this.quizeDto = quizeDto;
	}
	
	
	
}
