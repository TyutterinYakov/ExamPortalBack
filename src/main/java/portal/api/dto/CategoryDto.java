package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portal.api.dto.group.Create;
import portal.api.dto.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	@Null(groups = Create.class)
	@NotNull(groups = Update.class)
	private Long id;
	@NotBlank(groups = Create.class)
	@Size(max = 75, groups = {Create.class, Update.class})
	private String title;
	@Size(max = 1000, groups = {Create.class, Update.class})
	private String description;
	
}
