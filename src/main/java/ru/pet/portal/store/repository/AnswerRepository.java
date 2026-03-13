package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.store.entity.AnswerE;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<AnswerE, UUID> {

    @Query("select a from AnswerE a where a.question.id = :questionId")
    List<AnswerE> getAnswersByQuestionId(UUID questionId);
}
