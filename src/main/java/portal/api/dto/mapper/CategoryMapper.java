package portal.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import portal.api.dto.CategoryDto;
import portal.store.entity.Category;

@Component
public class CategoryMapper {

	public CategoryDto toDto(Category entity) {
		return new CategoryDto(
					entity.getId(),
					entity.getTitle(), 
					entity.getDescription()
				);
	}

	public List<CategoryDto> toDto(List<Category> entities){
		return entities
				.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

	public Category toEntity(CategoryDto categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getTitle(), categoryDto.getDescription());
	}
	
}
