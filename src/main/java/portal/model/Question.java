package portal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="question")
public class Question {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private Long questionId;
	@Column(name="content", length=4000)
	@NotEmpty
	private String content;
	@Column(name="image")
	private String image;
	@Transient
	private String givenAnswer;
	@NotEmpty
	private String option1;
	@NotEmpty
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@NotNull
	private Quize quize;

	
	
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public Quize getQuize() {
		return quize;
	}
	public void setQuize(Quize quize) {
		this.quize = quize;
	}
	
	@Override
	public String toString() {
		return "questionId: "+questionId+" content: "+content+" image: "+image
				+" givenAnswer: "+givenAnswer+" option1: "+option1+" option2: "+option2
				+" option3: "+option3+" option4: "+option4+" answer: "+answer
				+" quize: "+quize;
	}
	
	
	
	
}
