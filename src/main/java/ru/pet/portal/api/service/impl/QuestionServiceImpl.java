package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.service.QuestionService;
import ru.pet.portal.store.entity.Question;
import ru.pet.portal.store.entity.Quiz;
import ru.pet.portal.store.repository.QuestionRepository;
import ru.pet.portal.store.repository.QuizRepository;

import java.util.List;
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
    public void update(UUID questionId, Question question) {
        Question havingQuestion = questionRepository.findByIdWithThrow(questionId);
        question.setQuiz(havingQuestion.getQuiz());
        question.setId(questionId);
        questionRepository.save(question);
    }

    @Override
    public void create(UUID quizId, Question question) {
        final Quiz quiz = quizRepository.findByIdWithThrow(quizId);
        question.setQuiz(quiz);
        questionRepository.save(question);
    }

    @Override
    public Question getById(UUID questionId) {
        return questionRepository.findByIdWithThrow(questionId);
    }

    @Override
    public List<Question> getAllByQuizId(UUID quizId, int from, int size) {
        return questionRepository.getAllByQuizId(quizId, PageRequest.of(from, size, Sort.by("id")));
    }
}
