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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portal.api.dto.CategoryDto;

@Entity
@Table(name="category")
public class CategoryEntity {

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
	@JsonIgnore
	@OneToMany(mappedBy="category", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<QuizeEntity> quizies = new LinkedList<>();
	
	
	public CategoryEntity() {
		super();
	}
	public CategoryEntity(@NotEmpty Long categoryId, @NotEmpty String title, @NotEmpty String description) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
	}
	public CategoryEntity(@NotEmpty String title, @NotEmpty String description) {
		super();
		this.title = title;
		this.description = description;
	}
	public List<QuizeEntity> getQuizies() {
		return quizies;
	}
	public void setQuizies(List<QuizeEntity> quizies) {
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
	@Override
	public String toString() {
		return "categoryId: "+categoryId+" title: "+title+
				" description: "+description+" quizies: "+quizies;
	}
	
	public CategoryEntity makeCategoryDto(CategoryDto categoryDto) {
		this.categoryId=categoryDto.getCategoryId();
		this.description=categoryDto.getDescription();
		this.title=categoryDto.getTitle();
		return this;
	}
	
}
