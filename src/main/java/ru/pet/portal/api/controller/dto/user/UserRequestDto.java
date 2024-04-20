package ru.pet.portal.api.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotEmpty
    @Email
    private String email;

    @Size(min = 4, max = 30)
    @NotBlank
    private String password;

    @Size(min = 2, max = 30)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 30)
    @NotBlank
    private String lastName;
}
