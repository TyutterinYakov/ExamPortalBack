package portal.api.dto.mapper;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portal.api.dto.QuizRequestDto;
import portal.store.entity.Category;
import portal.store.entity.Quiz;


@Component
@RequiredArgsConstructor
public class QuizMapper {

	private final CategoryMapper categoryMapper;

//	public QuizeDto toDto(QuizeEntity entity) {
//		return new QuizeDto(
//				entity.getQuizeId(),
//				entity.getTitle(),
//				entity.getDescription(),
//				entity.isActive(),
//				entity.getMaxMarks(),
//				entity.getCountOfQuestion(),
//				categoryMapper.toDto(entity.getCategory()));
//	}
//
//	public List<QuizeDto> toDto(List<QuizeEntity> entities) {
//		return entities
//				.stream()
//				.map(this::toDto)
//			   .collect(Collectors.toList());
//	}

    public Quiz toEntity(QuizRequestDto requestDto, Category category) {
        return Quiz.builder()
                .active(requestDto.isActive())
                .category(category)
                .description(requestDto.getDescription())
                .title(requestDto.getTitle())
                .build();
    }

}
