package portal.api.dto.validation;

import portal.api.dto.AnswerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class CheckCollectionOneAnswerTrue implements ConstraintValidator<CheckCorrectlyResult, Collection<AnswerDto>> {

    @Override
    public void initialize(CheckCorrectlyResult constraintAnnotation) {
    }

    @Override
    public boolean isValid(Collection<AnswerDto> value, ConstraintValidatorContext context) {
        long countEmptyName = value.stream().map(AnswerDto::getReply).filter(String::isBlank).count();
        if (countEmptyName > 0) {
            return false;
        }
        if (value.size() != 4) {
            return false;
        }
        return value.stream().map(AnswerDto::isCorrectly).filter((b) -> b).count() == 1;
    }
}
