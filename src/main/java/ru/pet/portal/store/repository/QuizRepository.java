package ru.pet.portal.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.store.entity.Quiz;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    default Quiz findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Тест с идентификатором " + id + " отсутствует"));
    }

    @Query("select q from Quiz q join fetch q.category where q.category.id = :categoryId")
    List<Quiz> findAllByCategoryId(UUID categoryId, Pageable pageable);

    Optional<Quiz> findByIdAndActive(UUID id, boolean active);

    @Query("select q from Quiz q join fetch q.category where q.category.id = :categoryId and q.active = :active")
    List<Quiz> findAllByCategoryIdAndActive(UUID categoryId, Pageable pageable, boolean active);

    @Query("select q from Quiz q join fetch q.category where q.active = :active")
    List<Quiz> findAllByActive(Pageable pageable, boolean active);
    default Quiz findByIdAndActiveWithThrow(UUID id, boolean active) {
        return findByIdAndActive(id, active).orElseThrow(() ->
                new NotFoundException("Тест с идентификатором " + id + " отсутствует или он неактивен"));
    }
}
