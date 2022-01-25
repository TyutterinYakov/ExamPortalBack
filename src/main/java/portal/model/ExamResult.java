package portal.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
                    CascadeType.MERGE
                    
        })
	@JoinTable(
			name="answer_user",
			joinColumns = {@JoinColumn(name="answer", referencedColumnName = "answer_id")},
					inverseJoinColumns = {@JoinColumn(name="user_ids", referencedColumnName="user_id")}
			)
    @JsonIgnore
    private List<User> users = new LinkedList<>();
    
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
                    CascadeType.MERGE
                    
        })
	@JoinTable(
			name="answer_quize",
			joinColumns = {@JoinColumn(name="answer", referencedColumnName = "answer_id")},
					inverseJoinColumns = {@JoinColumn(name="quize", referencedColumnName="quize_id")}
			)
    @JsonIgnore
    private List<Quize> quizies = new LinkedList<>();
	
	
	public Long getAnswerId() {
		return answerId;
	}
	
	
	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}





	public List<Quize> getQuizies() {
		return quizies;
	}


	public void setQuizies(List<Quize> quizies) {
		this.quizies = quizies;
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
