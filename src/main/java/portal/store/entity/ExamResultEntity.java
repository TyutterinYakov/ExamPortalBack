package portal.store.entity;


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

import portal.api.util.HashMapConverter;


@Entity
@Table(name="quize_statistic")
public class ExamResultEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private Long answerId;
	private int validQuestion;
	private int invalidQuestion;
	private int skipQuestion;
	private int countPoints;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private QuizeEntity quize;
    @Convert(converter = HashMapConverter.class)
    private Map<String, Map<String, String>> questionsAndGivenAnswer; //Map<id  теста Map<Ответ, который дан; Правильный ответ>
   
	public ExamResultEntity() {
		super();
	}
	public ExamResultEntity(int validQuestion, int invalidQuestion, int skipQuestion, int countPoints, UserEntity user,
			QuizeEntity quize, Map<String, Map<String, String>> questionsAndGivenAnswer) {
		super();
		this.validQuestion = validQuestion;
		this.invalidQuestion = invalidQuestion;
		this.skipQuestion = skipQuestion;
		this.countPoints = countPoints;
		this.user = user;
		this.quize = quize;
		this.questionsAndGivenAnswer = questionsAndGivenAnswer;
	}
	public Map<String, Map<String, String>> getQuestionsAndGivenAnswer() {
		return questionsAndGivenAnswer;
	}
	public void setQuestionsAndGivenAnswer(Map<String, Map<String, String>> questionsAndGivenAnswer) {
		this.questionsAndGivenAnswer = questionsAndGivenAnswer;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public QuizeEntity getQuize() {
		return quize;
	}
	public void setQuize(QuizeEntity quize) {
		this.quize = quize;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public int getValidQuestion() {
		return validQuestion;
	}
	public void setValidQuestion(int validQustion) {
		this.validQuestion = validQustion;
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
	
	@Override
	public String toString() {
		return "answerId: "+answerId+" validQuestion "+validQuestion+" invalidQuestion "
				+invalidQuestion+" skipQuestion "+skipQuestion
				+" countPoints "+countPoints+" user "+user+" quize "+quize
				+"questionsAndGivenAnswer "+questionsAndGivenAnswer;
	}
	
	
}
