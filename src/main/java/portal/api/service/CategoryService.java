package portal.api.service;

import java.util.List;

import portal.api.dto.CategoryDto;
import portal.store.entity.CategoryEntity;

public interface CategoryService {

	//WRITE
	public CategoryDto addCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto);
	public void removeCategoryById(Long id);
	
	//READ
	public CategoryDto getCategoryById(Long id);
	public List<CategoryDto> getAllCategory();
	
	public CategoryEntity getCategoryEntityById(Long categoryId);
	
}
