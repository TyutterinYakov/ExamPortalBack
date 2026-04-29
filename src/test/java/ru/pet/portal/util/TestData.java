package ru.pet.portal.util;

import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import ru.pet.portal.store.entity.*;

@NoArgsConstructor
public class TestData {

    public static UserE generateUser() {
        return Instancio.of(UserE.class).create();
    }

    public static QuizE generateQuiz() {
        return Instancio.of(QuizE.class).create();
    }

    public static CategoryE generateCategory() {
        return Instancio.of(CategoryE.class).create();
    }

    public static PositionE generatePosition() {
        return Instancio.of(PositionE.class).create();
    }

    public static QuestionE generateQuestion() {
        return Instancio.of(QuestionE.class).create();
    }

    public static AnswerE generateAnswer() {
        return Instancio.of(AnswerE.class).create();
    }

    public static ExamResultE generateExamResult() {
        return Instancio.of(ExamResultE.class).create();
    }
}
