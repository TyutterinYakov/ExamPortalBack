package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.store.entity.PositionE;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<PositionE, UUID> {
    Optional<PositionE> findByName(String name);
    @Modifying
    @Query(value = "delete from quizzes_positions where quiz_id = :quizId", nativeQuery = true)
    void deleteByQuizId(UUID quizId);
}