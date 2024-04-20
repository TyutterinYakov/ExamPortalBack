package ru.pet.portal.api.controller.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pet.portal.api.controller.dto.QuizDto;
import ru.pet.portal.api.controller.dto.QuizRequestDto;
import ru.pet.portal.store.entity.Category;
import portal.store.entity.Quiz;


@Component
@RequiredArgsConstructor
public class QuizMapper {

	private final CategoryMapper categoryMapper;

	public QuizDto toDto(Quiz entity) { //TODO максимальная оценка и количество вопросов
		return new QuizDto(
				entity.getId(),
				entity.getTitle(),
				entity.getDescription(),
				entity.isActive(),
				0,
				0,
				categoryMapper.toDto(entity.getCategory()));
	}

	public List<QuizDto> toDto(List<Quiz> entities) {
		return entities
				.stream()
				.map(this::toDto)
			   .collect(Collectors.toList());
	}

    public Quiz toEntity(QuizRequestDto requestDto, Category category) {
        return Quiz.builder()
                .active(requestDto.getActive())
                .category(category)
                .description(requestDto.getDescription())
                .title(requestDto.getTitle())
                .build();
    }

}
