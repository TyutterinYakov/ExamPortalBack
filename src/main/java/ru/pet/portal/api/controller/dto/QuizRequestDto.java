//package ru.pet.portal.api.controller.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import ru.pet.portal.api.controller.dto.validation.group.Create;
//import ru.pet.portal.api.controller.dto.validation.group.Update;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;
//import javax.validation.constraints.Size;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class QuizRequestDto {
//    @Null(groups = Create.class)
//    @NotNull(groups = Update.class)
//    private Long id;
//    @Size(max = 75, groups = {Create.class, Update.class})
//    @NotBlank(groups = {Create.class})
//    private String title;
//    @Size(max = 1000, groups = {Create.class, Update.class})
//    private String description;
//    @NotNull(groups = {Create.class})
//    private Boolean active;
//    @NotNull(groups = {Create.class})
//    private Long categoryId;
//
//    public QuizRequestDto(String title, String description, Boolean active, Long categoryId) {
//        this.title = title;
//        this.description = description;
//        this.active = active;
//        this.categoryId = categoryId;
//    }
//}
