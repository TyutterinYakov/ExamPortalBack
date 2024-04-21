package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizService {

    void create(UUID categoryId, Quiz quiz);

    void deleteById(UUID uuid);

    List<Quiz> getAllByCategoryId(UUID uuid, int from, int size);

    Quiz getById(UUID quizId);

    void update(UUID categoryId, Quiz entity, UUID quizId);


    List<Quiz> getAll(int from, int size);
}
