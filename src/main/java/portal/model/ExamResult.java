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
