package portal.api.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

	private Long categoryId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	
	public CategoryDto() {
		super();
	}
	public CategoryDto(Long categoryId, String title, String description) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
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
