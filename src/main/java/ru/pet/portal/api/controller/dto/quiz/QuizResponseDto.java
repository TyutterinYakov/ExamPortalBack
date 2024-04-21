package ru.pet.portal.api.controller.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pet.portal.api.controller.dto.category.CategoryResponseDto;

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
	private int maxMarks;
	private int countOfQuestion;
	private CategoryResponseDto category;

}
