package portal.api.service;

import java.util.List;

import portal.api.dto.CategoryDto;

public interface CategoryService {

	public void add(CategoryDto categoryDto);

	public void update(CategoryDto categoryDto);

	public void deleteById(long id);

	public CategoryDto getById(long id);

	public List<CategoryDto> getAll();

}
