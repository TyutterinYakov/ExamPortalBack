package ru.pet.portal.api.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.api.service.model.ExamResult;
import ru.pet.portal.api.service.model.QuizSpecification;
import ru.pet.portal.store.entity.*;
import ru.pet.portal.store.repository.ExamResultRepository;
import ru.pet.portal.store.repository.QuestionRepository;
import ru.pet.portal.store.repository.QuizRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamResultServiceImpl implements ExamResultService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final ExamResultRepository examResultRepository;

    @Override
    public ExamResult submitExam(UUID quizId, UserE user, List<AnswerExamRequest> answers) {
        final Map<UUID, QuestionE> questionById = questionRepository.getByQuizIdAndActiveQuiz(quizId, true).stream().collect(Collectors.toMap(QuestionE::getId, Function.identity()));

        if (questionById.isEmpty()) {
            throw new NotFoundException("Некорректный quiz с идентификатором " + quizId);
        }

        final Map<UUID, Pair<Answer, Integer>> answerCorrectlyByQuestionId = questionById.values().stream()
                .collect(Collectors.toMap(QuestionE::getId, q -> new ImmutablePair<>(
                        q.getAnswers().stream().filter(Answer::isCorrectly)
                                .findFirst().orElseThrow(), q.getMarks())));

        final ExamResult examResult = new ExamResult();

        final List<ExamAnswer> results = answers.stream().map(a -> {
            final Pair<Answer, Integer> answerAndMarks = answerCorrectlyByQuestionId.get(a.getQuestionId());

            return checkGivenAnswer(a, examResult, answerAndMarks, questionById);
        }).toList();

        final QuizE quiz = quizRepository.findByIdWithThrow(quizId);

        List<QuizSpecification> quizSpecifications = questionRepository.getQuizSpecifications(List.of(quiz));
        examResultRepository.save(new ExamResultE()
                .setUser(user)
                .setExamAnswers(results)
                .setQuiz(quiz)
                .setCountPoints(examResult.getCountPoints())
                .setValidQuestion(examResult.getValidQuestion())
                .setInvalidQuestion(examResult.getInvalidQuestion())
                .setMaxMarks(quizSpecifications.get(0).getMaxMarks())
                .setSkipQuestion(examResult.getSkipQuestion()));
        return examResult;
    }

    @Override
    public List<ExamResultE> getAllUserResults(UUID userId) {
        return examResultRepository.findAllByUserId(userId);
    }

    @Override
    public List<ExamResultE> getAllByQuizId(UUID quizId) {
        return examResultRepository.findAllByQuizId(quizId);
    }

    @Override
    public void deleteById(UUID resultId) {
        examResultRepository.deleteById(resultId);
    }

    @Override
    public boolean examHasBeenSolvedByUser(UUID userId, UUID quizId) {
        return examResultRepository.existsByQuizIdAndUserId(userId, quizId);
    }

    private static ExamAnswer checkGivenAnswer(AnswerExamRequest a,
                                               ExamResult examResult,
                                               Pair<Answer, Integer> answerAndMarks,
                                               Map<UUID, QuestionE> questionById) {

        final String givenAnswer = a.getGivenAnswer();
        final String correctlyAnswer = answerAndMarks.getLeft().getReply();
        //Проверка, что на вопрос был дан ответ
        if (StringUtils.isBlank(givenAnswer)) {
            examResult.incSkipQuestion();
        } else {
            //Проверка, что на вопрос был дан правильный ответ
            if (Objects.equals(givenAnswer, correctlyAnswer)) {
                examResult.incValidQuestion();
                examResult.recalculateCountPoints(answerAndMarks.getRight());
            } else {
                examResult.incInvalidQuestion();
            }
        }
        return new ExamAnswer()
                .setGivenAnswer(givenAnswer)
                .setAnswer(correctlyAnswer)
                .setQuestionContent(questionById.get(a.getQuestionId()).getContent());
    }
}
