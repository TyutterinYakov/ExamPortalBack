package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portal.api.dto.group.Create;
import portal.api.dto.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	@NotNull(groups = Update.class)
	private Long id;
	@NotBlank(groups = Create.class)
	private String title;
	private String description;
	
}
