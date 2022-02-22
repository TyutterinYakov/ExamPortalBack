package portal.store.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portal.api.dto.QuizeDto;

@Entity
@Table(name="quize")
public class QuizeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quize_id")
	private Long quizeId;
	@Column(name="title")
	@NotEmpty
	private String title;
	@Column(name="description", length=1000)
	@NotEmpty
	private String description;
	@Column(name="active")
	private boolean active=false;
	@NotNull
	@Column(name="max_marks")
	private int maxMarks;
	@Column(name="count_of_question")
	private int countOfQuestion=0;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@NotNull
	@Valid
	private CategoryEntity category;
	@JsonIgnore
	@OneToMany(mappedBy="quize", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<QuestionEntity> questions = new LinkedList<>();
	
	
	
	public List<QuestionEntity> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}
	public int getCountOfQuestion() {
		return countOfQuestion;
	}
	public void setCountOfQuestion(int countOfQuestion) {
		this.countOfQuestion = countOfQuestion;
	}
	public Long getQuizeId() {
		return quizeId;
	}
	public void setQuizeId(Long quizeId) {
		this.quizeId = quizeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "quizeId: "+quizeId+" title: "+title+" description: "
				+description+" active: "+active+" maxMarks: "
				+maxMarks+" countOfQuestion: "+countOfQuestion
				+" category: "+category+" questions: "+questions;
	}
	
	
	public QuizeEntity makeQuizeDtoTo(QuizeDto quizeDto, CategoryEntity category) {
		this.active=quizeDto.isActive();
		this.category=category;
		this.description=quizeDto.getDescription();
		this.maxMarks=quizeDto.getMaxMarks();
		this.title=quizeDto.getTitle();
		this.quizeId=quizeDto.getQuizeId();
		return this;
	}
	
	
	
	
}
