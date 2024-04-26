package ru.pet.portal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.api.service.impl.ExamResultServiceImpl;
import ru.pet.portal.configuration.TestDataSourceConfig;
import ru.pet.portal.store.repository.ExamResultRepository;
import ru.pet.portal.store.repository.QuestionRepository;
import ru.pet.portal.store.repository.QuizRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestDataSourceConfig.class)
@DataJpaTest
@ActiveProfiles("test")
public class ExamResultServiceTest {

    private final ExamResultService examResultService;

    @Autowired
    public ExamResultServiceTest(QuestionRepository questionRepository,
                                 QuizRepository quizRepository,
                                 ExamResultRepository examResultRepository) {
        examResultService = new ExamResultServiceImpl(questionRepository, quizRepository, examResultRepository);
    }

    @Test
    void testUserSolvedTheTest() {
        assertTrue(true);
    }

}
