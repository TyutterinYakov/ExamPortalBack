package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.QuizE;
import ru.pet.portal.store.entity.UserE;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface QuizService {

    QuizE create(UUID categoryId, QuizE quiz, Set<UUID> positions);

    void deleteById(UUID uuid);

    List<QuizE> getAllByCategoryId(UUID uuid, int from, int size);

    QuizE getById(UUID quizId);

    void update(UUID categoryId, QuizE entity, UUID quizId, Set<UUID> positionIds);


    List<QuizE> getAll(int from, int size);

    QuizE getByIdAndActive(UUID quizId);

    List<QuizE> getAllByActive(int from, int size, UserE user);

    List<QuizE> getActiveQuizzesByCategoryId(UUID categoryId, int from, int size, UserE user);
}
