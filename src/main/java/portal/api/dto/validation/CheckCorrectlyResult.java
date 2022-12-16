package portal.api.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckCollectionOneAnswerTrue.class)
public @interface CheckCorrectlyResult {
    String message() default "the number of correct answers is greater than 1 or equal to 0. Or size collection != 4";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
