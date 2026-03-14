package ru.pet.portal.api.controller.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pet.portal.api.controller.dto.category.CategoryResponseDto;
import ru.pet.portal.api.controller.dto.position.PositionResponseDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResponseDto {

	private UUID id;
	private String title;
	private String description;
	private boolean active;
	private long maxMarks;
	private long countOfQuestion;
	private long time;
	private CategoryResponseDto category;
	private List<PositionResponseDto> positions;

}
