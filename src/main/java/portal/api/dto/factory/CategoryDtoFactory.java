package portal.api.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import portal.api.dto.CategoryDto;
import portal.store.entity.CategoryEntity;

@Component
public class CategoryDtoFactory {

	
	public CategoryDto createCategoryDto(CategoryEntity entity) {
		
		return new CategoryDto(
					entity.getCategoryId(), 
					entity.getTitle(), 
					entity.getDescription()
				);
	}
	
	
	public List<CategoryDto> createListCategoryDto(List<CategoryEntity> entities){
		
		return entities
				.stream()
				.map(this::createCategoryDto)
				.collect(Collectors.toList());
	}
	
	
}
