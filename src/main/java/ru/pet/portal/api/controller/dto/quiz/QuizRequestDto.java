package ru.pet.portal.api.controller.dto.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.pet.portal.api.controller.dto.validation.group.Create;
import ru.pet.portal.api.controller.dto.validation.group.Update;

import java.util.UUID;

@Getter
@Setter
public class QuizRequestDto {
    @Size(min = 3, max = 30, groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class})
    private String title;
    @Size(min = 3, max = 1000, groups = {Create.class, Update.class})
    private String description;
    @NotNull(groups = {Create.class})
    private Boolean active;
    @NotNull(groups = {Create.class})
    private UUID categoryId;

}
