package ru.pet.portal.api.controller.dto.validation.group;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;
import ru.pet.portal.api.controller.dto.answer.AnswerRequestDto;

import java.util.List;
import java.util.Set;

public class CheckExistsCorrectlyResultValidator implements ConstraintValidator<CheckExistsCorrectlyAnswer, Set<AnswerRequestDto>> {

    @Override
    public boolean isValid(Set<AnswerRequestDto> answerRequestDtos, ConstraintValidatorContext constraintValidatorContext) {
        if (CollectionUtils.isEmpty(answerRequestDtos) || answerRequestDtos.size() < 2) {
            return false;
        }
        if (answerRequestDtos.stream().distinct().toList().size() != answerRequestDtos.size()) {
            return false;
        }
        final List<AnswerRequestDto> correctlyAnswer = answerRequestDtos.stream()
                .filter(AnswerRequestDto::isCorrectly).toList();
        return correctlyAnswer.size() == 1;
    }
}
