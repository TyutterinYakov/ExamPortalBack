package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizDto {

	private Long id;
	private String title;
	private String description;
	private boolean active;
	private int maxMarks; //TODO
	private int countOfQuestion; //TODO
	private CategoryDto categoryDto;

}
