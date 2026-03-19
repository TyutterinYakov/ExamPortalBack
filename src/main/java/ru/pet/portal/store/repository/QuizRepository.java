package ru.pet.portal.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.store.entity.QuizE;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Query(value = """
            select q.* from quizzes q
            left join quizzes_positions qp on qp.quiz_id = q.id
            left join positions p ON p.id = qp.position_id
            left join users_positions up on up.position_id = p.id
            where q.active = :isActive and up.user_id = :userId and q.id not in :quizIdsNotIn
            """, nativeQuery = true)
    List<QuizE> findAllByActive(Pageable pageable, boolean isActive, UUID userId, Set<UUID> quizIdsNotIn);

    @Query(value = """
            select q.* from quizzes q
            where q.id in :ids
            """, nativeQuery = true)
    List<QuizE> findAllByIds(Set<UUID> ids, Pageable pageable);


    @Query("select q.id, q.title from QuizE q where q.id not in :quizIdsNotIn")
    Set<Object[]> findTitleAndId(Set<UUID> quizIdsNotIn);

    default QuizE findByIdAndActiveWithThrow(UUID id, boolean active) {
        return findByIdAndActive(id, active).orElseThrow(() ->
                new NotFoundException("Тест с идентификатором " + id + " отсутствует или он неактивен"));
    }
}
