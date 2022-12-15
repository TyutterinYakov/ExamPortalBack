package portal.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class QuizeDto {

	private Long quizeId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	private boolean active=false;
	@NotNull
	private int maxMarks;
	private int countOfQuestion;
	@NotNull
	private CategoryDto categoryDto;
	
	
	public QuizeDto() {
		super();
	}

	public QuizeDto(Long quizeId, @NotEmpty String title, @NotEmpty String description, boolean active,
			@NotNull int maxMarks,int countOfQuestion, @NotNull CategoryDto categoryDto) {
		super();
		this.quizeId = quizeId;
		this.title = title;
		this.description = description;
		this.active = active;
		this.maxMarks = maxMarks;
		this.countOfQuestion = countOfQuestion;
		this.categoryDto = categoryDto;
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

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public int getCountOfQuestion() {
		return countOfQuestion;
	}

	public void setCountOfQuestion(int countOfQuestion) {
		this.countOfQuestion = countOfQuestion;
	}
	
	
	
	
	
}
