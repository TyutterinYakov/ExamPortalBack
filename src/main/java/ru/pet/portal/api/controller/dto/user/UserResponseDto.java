package ru.pet.portal.api.controller.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
public class UserResponseDto {
    private String email;
    private String role;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private byte[] imageProfile;
    private Set<String> interests;
    private Set<String> positions;
}
