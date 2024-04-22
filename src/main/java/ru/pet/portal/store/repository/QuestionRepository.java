package ru.pet.portal.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.api.service.model.QuizSpecification;
import ru.pet.portal.store.entity.QuestionE;
import ru.pet.portal.store.entity.QuizE;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface QuestionRepository extends JpaRepository<QuestionE, UUID> {

    @Query("select new ru.pet.portal.api.service.model.QuizSpecification(q.quiz.id, sum(q.marks), sum(q.time), count(q)) " +
            "from QuestionE q where q.quiz IN :quizzes GROUP BY q.quiz.id")
    List<QuizSpecification> getQuizSpecifications(List<QuizE> quizzes);


    default Map<UUID, QuizSpecification> getQuizSpecificationByQuizId(List<QuizE> quizzes) {
        return getQuizSpecifications(quizzes).stream().collect(Collectors
                .toMap(QuizSpecification::getQuizId, Function.identity()));
    }

    default QuestionE findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Вопрос с идентификатором " + id + " отсутствует"));
    }

    @Query("select q from QuestionE q join fetch q.quiz where q.id = :id")
    Optional<QuestionE> findById(UUID id);

    @Query("select q from QuestionE q where q.quiz.id = :quizId")
    List<QuestionE> getAllByQuizId(UUID quizId, Pageable pageable);

    @Query("select q from QuestionE q where q.quiz.id = :quizId and q.quiz.active = :active")
    List<QuestionE> getByQuizIdAndActiveQuiz(UUID quizId, boolean active);

}
