package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.QuizE;

import java.util.List;
import java.util.UUID;

public interface QuizService {

    void create(UUID categoryId, QuizE quiz);

    void deleteById(UUID uuid);

    List<QuizE> getAllByCategoryId(UUID uuid, int from, int size);

    QuizE getById(UUID quizId);

    void update(UUID categoryId, QuizE entity, UUID quizId);


    List<QuizE> getAll(int from, int size);

    QuizE getByIdAndActive(UUID quizId);

    List<QuizE> getAllByActive(int from, int size);

    List<QuizE> getActiveQuizzesByCategoryId(UUID categoryId, int from, int size);
}
