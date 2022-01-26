package portal.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;
	@NotEmpty
	@Column(name="title")
	private String title;
	@Column(name="description", length=1000)
	@NotEmpty
	private String description;
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Set<Quize> quizies = new HashSet<>();
	
	
	public Set<Quize> getQuizies() {
		return quizies;
	}
	public void setQuizies(Set<Quize> quizies) {
		this.quizies = quizies;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	
}
