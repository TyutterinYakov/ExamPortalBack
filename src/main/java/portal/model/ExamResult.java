package portal.model;


import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import portal.util.HashMapConverter;


@Entity
@Table(name="quize_statistic")
public class ExamResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private Long answerId;
	private int validQustion;
	private int invalidQuestion;
	private int skipQuestion;
	private int countPoints;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Quize quize;
    @Convert(converter = HashMapConverter.class)
    private Map<String, Map<String, String>> questionsAndGivenAnswer; //Map<id  теста Map<Ответ, который дан; Правильный ответ>
    
   


	public Map<String, Map<String, String>> getQuestionsAndGivenAnswer() {
		return questionsAndGivenAnswer;
	}




	public void setQuestionsAndGivenAnswer(Map<String, Map<String, String>> questionsAndGivenAnswer) {
		this.questionsAndGivenAnswer = questionsAndGivenAnswer;
	}




	public Long getAnswerId() {
		return answerId;
	}
	



	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public Quize getQuize() {
		return quize;
	}




	public void setQuize(Quize quize) {
		this.quize = quize;
	}




	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public int getValidQustion() {
		return validQustion;
	}
	public void setValidQustion(int validQustion) {
		this.validQustion = validQustion;
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
	
	
	
}
