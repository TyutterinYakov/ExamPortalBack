package portal.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestDto {
    @NotBlank
    private String title;
    private String description;
    private boolean active = true;
    @NotNull
    private Long categoryId;

}
