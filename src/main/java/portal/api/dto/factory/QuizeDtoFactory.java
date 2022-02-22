package portal.api.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import portal.api.dto.QuizeDto;
import portal.store.entity.QuizeEntity;

@Component
public class QuizeDtoFactory {

	private final CategoryDtoFactory categoryDtoFactory;
	
	@Autowired
	public QuizeDtoFactory(CategoryDtoFactory categoryDtoFactory) {
		super();
		this.categoryDtoFactory = categoryDtoFactory;
	}


	public QuizeDto createQuizeDto(QuizeEntity entity) {
		return new QuizeDto(
				entity.getQuizeId(),
				entity.getTitle(),
				entity.getDescription(),
				entity.isActive(),
				entity.getMaxMarks(),
				entity.getCountOfQuestion(),
				categoryDtoFactory
					.createCategoryDto(
							entity.getCategory()));
	}
	
	
	public List<QuizeDto> createListQuizeDto(List<QuizeEntity> entities) {
		return entities
				.stream()
				.map(this::createQuizeDto)
			   .collect(Collectors.toList());
	}
}
