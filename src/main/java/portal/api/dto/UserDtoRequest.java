package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {
    @NotBlank
    @Email
    private String email;
    @Size(max = 30, min=4, message="Size userName min4 or max30")
    @NotBlank
    private String password;
    @Size(max = 30, min=2, message="Size firstName min4 or max30")
    @NotBlank
    private String firstName;
    @Size(max = 30, min=2, message="Size lastName min4 or max30")
    @NotBlank
    private String lastName;
    @Size(max = 15, min=4)
    @NotBlank
    private String phone;
}
