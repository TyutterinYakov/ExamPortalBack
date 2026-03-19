package ru.pet.portal.api.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.pet.portal.api.service.GigaChatService;
import ru.pet.portal.api.service.QuizService;
import ru.pet.portal.api.service.model.QuizSpecification;
import ru.pet.portal.store.entity.*;
import ru.pet.portal.store.repository.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final PositionRepository positionRepository;
    private final GigaChatService gigaChatService;
    private final ExamResultRepository examResultRepository;

    @Override
    public QuizE create(UUID categoryId, QuizE quiz, Set<UUID> positionIds) {
        CategoryE categoryE = categoryRepository.findByIdWithThrow(categoryId);
        quiz.setCategory(categoryE);
        if (!CollectionUtils.isEmpty(positionIds)) {
            final List<PositionE> positions = positionRepository.findAllById(positionIds);
            quiz.setPositions(positions);
        }

        return quizRepository.saveAndFlush(quiz);
    }

    @Override
    public void deleteById(UUID id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<QuizE> getAllByCategoryId(UUID categoryId, int from, int size) {
        final List<QuizE> quizzes = quizRepository.findAllByCategoryId(categoryId, PageRequest.of(from, size, Sort.by("title")));
        setQuizSpecification(quizzes);
        return quizzes;
    }

    @Override
    public QuizE getById(UUID quizId) {
        return quizRepository.findByIdWithThrow(quizId);
    }

    @Override
    @Transactional
    public void update(UUID categoryId, QuizE quiz, UUID quizId, Set<UUID> positionIds) {
        QuizE havingQuiz = quizRepository.findByIdWithThrow(quizId);
        if (categoryId != null) {
            havingQuiz.setCategory(categoryRepository.findByIdWithThrow(categoryId));
        }
        final String title = quiz.getTitle();
        if (!StringUtils.isBlank(title)) {
            havingQuiz.setTitle(title);
        }
        final String description = quiz.getDescription();
        if (!StringUtils.isBlank(description)) {
            havingQuiz.setDescription(description);
        }
        final Boolean active = quiz.getActive();
        if (active != null) {
            havingQuiz.setActive(active);
        }

        positionRepository.deleteByQuizId(havingQuiz.getId());
        if (!CollectionUtils.isEmpty(positionIds)) {
            final List<PositionE> positions = positionRepository.findAllById(positionIds);
            havingQuiz.setPositions(positions);
        }
        quizRepository.save(havingQuiz);
    }

    @Override
    public List<QuizE> getAll(int from, int size) {
        final List<QuizE> quizzes = quizRepository.findAll(PageRequest.of(from, size, Sort.by("title")))
                .getContent();
        setQuizSpecification(quizzes);
        return quizzes;
    }

    @Override
    public QuizE getByIdAndActive(UUID quizId) {
        final QuizE quiz = quizRepository.findByIdAndActiveWithThrow(quizId, true);
        setQuizSpecification(quiz);
        return quiz;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizE> getAllByActive(int from, int size, UserE user) {
        final UUID userId = user.getId();
        //Получаем идентификаторы пройденных тестов. Они не должны попасть в выборку
        final Set<UUID> excludeQuizIds = examResultRepository.findQuizIdsByUserId(userId);

        //Получаем тесты, которые пользователь обязательно должен пройти по должности
        final List<QuizE> quizzes = quizRepository.findAllByActive(
                PageRequest.of(from, size, Sort.by("title")), true, userId, excludeQuizIds);

        //Проставляем признак обязательности
        quizzes.forEach(q -> q.setRequired(true));

        //Если найдено тестов меньше, чем запрашивалось - пробуем добить рекоммендациями
        if (quizzes.size() < size) {
            //Добавляем в исключения те тесты, что уже отобраны
            excludeQuizIds.addAll(quizzes.stream().map(QuizE::getId).toList());
            final Set<Object[]> quizWithNameAndId = quizRepository.findTitleAndId(excludeQuizIds);
            final Set<UUID> recommendations = gigaChatService.getRecommendations(user.getInterests(), quizWithNameAndId);
            if (!recommendations.isEmpty()) {
                //Обогащаем результат теми, что были получены из рекоммендаций
                quizzes.addAll(
                        quizRepository.findAllByIds(recommendations, PageRequest.of(0,
                                size - quizzes.size(), Sort.by("title")))
                );
            }
        }


        setQuizSpecification(quizzes);
        return quizzes;
    }

    @Override
    public List<QuizE> getActiveQuizzesByCategoryId(UUID categoryId, int from, int size, UserE user) {
        final List<QuizE> quizzes = quizRepository.findAllByCategoryIdAndActive(categoryId,
                PageRequest.of(from, size, Sort.by("title")), true);
        setQuizSpecification(quizzes);
        return quizzes;
    }

    private void setQuizSpecification(QuizE quiz) {
        setQuizSpecification(List.of(quiz));
    }

    private void setQuizSpecification(List<QuizE> quizzes) {
        final Map<UUID, QuizSpecification> quizSpecificationByQuizId =
                questionRepository.getQuizSpecificationByQuizId(quizzes);
        quizzes.forEach(quiz -> Optional.ofNullable(quizSpecificationByQuizId.get(quiz.getId()))
                .ifPresent(s ->
                        quiz.setCountOfQuestion(s.getCountOfQuestion())
                                .setMaxMarks(s.getMaxMarks())
                                .setTime(s.getTime()))
        );
    }
}
