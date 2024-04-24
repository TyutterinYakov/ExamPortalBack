package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.store.entity.ExamResultE;

import java.util.List;
import java.util.UUID;

public interface ExamResultRepository extends JpaRepository<ExamResultE, UUID> {
    @Query("select e from ExamResultE e join fetch e.quiz where e.user.id = :userId")
    List<ExamResultE> findAllByUserId(UUID userId);

    @Query("select e from ExamResultE e where e.quiz.id = :quizId")
    List<ExamResultE> findAllByQuizId(UUID quizId);

    @Query("select count(*) > 0 from ExamResultE e where e.user.id = :userId and e.quiz.id = :quizId")
    boolean existsByQuizIdAndUserId(UUID userId, UUID quizId);
}
