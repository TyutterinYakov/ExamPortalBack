package ru.pet.portal.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.store.entity.QuizE;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<QuizE, UUID> {

    default QuizE findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Тест с идентификатором " + id + " отсутствует"));
    }

    @Query("select q from QuizE q join fetch q.category where q.category.id = :categoryId")
    List<QuizE> findAllByCategoryId(UUID categoryId, Pageable pageable);

    Optional<QuizE> findByIdAndActive(UUID id, boolean active);

    @Query("select q from QuizE q join fetch q.category where q.category.id = :categoryId and q.active = :active")
    List<QuizE> findAllByCategoryIdAndActive(UUID categoryId, Pageable pageable, boolean active);

    @Query("select q from QuizE q join fetch q.category where q.active = :active")
    List<QuizE> findAllByActive(Pageable pageable, boolean active);
    default QuizE findByIdAndActiveWithThrow(UUID id, boolean active) {
        return findByIdAndActive(id, active).orElseThrow(() ->
                new NotFoundException("Тест с идентификатором " + id + " отсутствует или он неактивен"));
    }
}
