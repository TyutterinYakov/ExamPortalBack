package ru.pet.portal.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.api.service.model.QuizSpecification;
import ru.pet.portal.store.entity.Question;
import ru.pet.portal.store.entity.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query("select new ru.pet.portal.api.service.model.QuizSpecification(q.quiz.id, sum(q.marks), sum(q.time), count(q)) " +
            "from Question q where q.quiz IN :quizzes GROUP BY q.quiz.id")
    List<QuizSpecification> getQuizSpecifications(List<Quiz> quizzes);


    default Map<UUID, QuizSpecification> getQuizSpecificationByQuizId(List<Quiz> quizzes) {
        return getQuizSpecifications(quizzes).stream().collect(Collectors
                .toMap(QuizSpecification::getQuizId, Function.identity()));
    }

    default Question findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Вопрос с идентификатором " + id + " отсутствует"));
    }

    @Query("select q from Question q join fetch q.quiz where q.id = :id")
    Optional<Question> findById(UUID id);

    @Query("select q from Question q where q.quiz.id = :quizId")
    List<Question> getAllByQuizId(UUID quizId, Pageable pageable);
}
