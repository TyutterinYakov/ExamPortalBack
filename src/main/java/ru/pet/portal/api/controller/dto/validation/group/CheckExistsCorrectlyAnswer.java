package ru.pet.portal.api.controller.dto.validation.group;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckExistsCorrectlyResultValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckExistsCorrectlyAnswer {
    String message() default "Correctly answer is not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
