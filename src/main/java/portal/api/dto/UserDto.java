package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	@Size(max = 30, min=4)
	private String firstName;
	@Size(max = 30, min=2)
	private String lastName;
	@Size(max = 40, min=4)
	private String email;
	@Size(max = 15, min=4)
	private String phone;

}
