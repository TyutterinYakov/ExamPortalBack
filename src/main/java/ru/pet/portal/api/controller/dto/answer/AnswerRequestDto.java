package ru.pet.portal.api.controller.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AnswerRequestDto {

    private boolean correctly;
    @NotBlank
    @Size(min = 2, max = 300)
    private String reply;
}
