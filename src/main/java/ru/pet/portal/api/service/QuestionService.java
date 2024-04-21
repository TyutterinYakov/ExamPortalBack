package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    void deleteById(UUID questionId);


    void update(UUID questionId, Question question);

    void create(UUID quizId, Question question);


    Question getById(UUID questionId);

    List<Question> getAllByQuizId(UUID quizId, int from, int size);

}
