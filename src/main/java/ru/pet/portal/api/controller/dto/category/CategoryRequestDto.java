package ru.pet.portal.api.controller.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pet.portal.api.controller.dto.validation.group.Create;
import ru.pet.portal.api.controller.dto.validation.group.Update;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {

	@NotBlank(groups = Create.class)
	@Size(max = 75, min = 3, groups = {Create.class, Update.class})
	private String title;
	@Size(max = 1000, min = 3, groups = {Create.class, Update.class})
	@NotBlank(groups = Create.class)
	private String description;
	
}
