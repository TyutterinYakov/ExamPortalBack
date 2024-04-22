package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.service.QuestionService;
import ru.pet.portal.api.service.model.QuizSpecification;
import ru.pet.portal.store.entity.QuestionE;
import ru.pet.portal.store.entity.QuizE;
import ru.pet.portal.store.repository.QuestionRepository;
import ru.pet.portal.store.repository.QuizRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public void deleteById(UUID questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public void update(UUID questionId, QuestionE questionE) {
        QuestionE havingQuestionE = questionRepository.findByIdWithThrow(questionId);
        questionE.setQuiz(havingQuestionE.getQuiz());
        questionE.setId(questionId);
        questionRepository.save(questionE);
    }

    @Override
    public void create(UUID quizId, QuestionE questionE) {
        final QuizE quiz = quizRepository.findByIdWithThrow(quizId);
        questionE.setQuiz(quiz);
        questionRepository.save(questionE);
    }

    @Override
    public QuestionE getById(UUID questionId) {
        return questionRepository.findByIdWithThrow(questionId);
    }

    @Override
    public List<QuestionE> getAllByQuizId(UUID quizId, int from, int size) {
        return questionRepository.getAllByQuizId(quizId, PageRequest.of(from, size, Sort.by("id")));
    }

    @Override
    public List<QuestionE> getByQuizIdAndActiveQuiz(UUID quizId) {
        final QuizE quiz = quizRepository.findByIdAndActiveWithThrow(quizId, true);
        final List<QuestionE> questionES = questionRepository.getByQuizIdAndActiveQuiz(quizId, true);
        if (!questionES.isEmpty()) {
            setSpecificationForQuiz(quiz);
        }
        return questionES;
    }

    private void setSpecificationForQuiz(QuizE quiz) {
        final Map<UUID, QuizSpecification> specifications =
                questionRepository.getQuizSpecificationByQuizId(List.of(quiz));
        Optional.ofNullable(specifications.get(quiz.getId()))
                .ifPresent(q -> quiz
                        .setCountOfQuestion(q.getCountOfQuestion())
                        .setMaxMarks(q.getMaxMarks())
                        .setTime(q.getTime()));
    }
}
