package ru.pet.portal.api.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.portal.api.controller.dto.mapper.AnswerMapper;
import ru.pet.portal.api.controller.dto.mapper.QuestionMapper;
import ru.pet.portal.api.controller.dto.mapper.QuizMapper;
import ru.pet.portal.api.service.QuestionService;
import ru.pet.portal.api.service.QuizService;
import ru.pet.portal.api.service.dto.gigachat.GeneratedQuiz;
import ru.pet.portal.store.entity.AnswerE;
import ru.pet.portal.store.entity.QuestionE;
import ru.pet.portal.store.entity.QuizE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuizFacade {

    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Transactional
    public void generate(GeneratedQuiz rq, UUID categoryId) {
        final QuizE quiz = quizService.create(categoryId, quizMapper.toEntity(rq, false));
        final List<QuestionE> questionsToSave = new ArrayList<>(rq.getQuestions().size());
        for (GeneratedQuiz.GeneratedQuestion question : rq.getQuestions()) {
            final QuestionE questionToSave = questionMapper.toEntity(question, quiz);
            final List<AnswerE> answers = question.getAnswers().stream().map(a ->
                    answerMapper.toEntity(a, questionToSave)).toList();
            questionToSave.setAnswers(answers);
            questionsToSave.add(questionToSave);
        }
        questionService.create(questionsToSave);
    }
}
