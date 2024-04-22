package ru.pet.portal.api.service;

import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;
import ru.pet.portal.store.entity.ExamResultE;
import ru.pet.portal.store.entity.UserE;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ExamResultService {
    ExamResult submitExam(UUID quizId, UserE user, List<AnswerExamRequest> answers);

    List<ExamResultE> getAllUserResults(UUID id);

    List<ExamResultE> getAllByQuizId(UUID quizId);

    void deleteById(UUID resultId);

}
