package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizDto {

	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private boolean active;
	private int maxMarks;
	private int countOfQuestion;
	@NotNull
	private CategoryDto categoryDto;

}
