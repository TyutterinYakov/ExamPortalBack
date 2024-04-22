package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.QuestionE;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    void deleteById(UUID questionId);


    void update(UUID questionId, QuestionE questionE);

    void create(UUID quizId, QuestionE questionE);


    QuestionE getById(UUID questionId);

    List<QuestionE> getAllByQuizId(UUID quizId, int from, int size);

    List<QuestionE> getByQuizIdAndActiveQuiz(UUID quizId);
}
