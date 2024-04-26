package ru.pet.portal.util;

import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import ru.pet.portal.store.entity.QuizE;
import ru.pet.portal.store.entity.UserE;

@NoArgsConstructor
public class TestData {

    public static UserE generateUser() {
        return Instancio.of(UserE.class).create();
    }

    public static QuizE generateQuiz() {
        return Instancio.of(QuizE.class).create();
    }
}
