package portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import portal.api.dto.QuizDto;
import portal.api.dto.QuizRequestDto;
import portal.api.dto.mapper.QuizMapper;
import portal.api.exception.NotFoundException;
import portal.api.service.QuizService;
import portal.store.entity.Category;
import portal.store.entity.Quiz;
import portal.store.repository.CategoryRepository;
import portal.store.repository.QuizRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final CategoryRepository categoryRepository;
	private final QuizRepository quizRepository;
	private final QuizMapper quizMapper;

	@Override
	@Transactional
	public void add(QuizRequestDto quizDto) {
		Category category = findCategoryOrThrow(quizDto.getCategoryId());
		Quiz quiz = quizMapper.toEntity(quizDto, category);
		quizRepository.save(quiz);
	}

	@Override
	@Transactional
	public void deleteById(long quizId) {
		quizRepository.findById(quizId).ifPresent((q) -> quizRepository.deleteById(quizId));
	}

	@Override
	public List<QuizDto> getAllByCategory(long categoryId) {
		return quizMapper.toDto(quizRepository.findAllByCategoryId(categoryId));
	}

	@Override
	public QuizDto getById(Long quizId) {
		return quizMapper.toDto(findByIdOrThrow(quizId));
	}

	@Override
	@Transactional
	public void update(QuizRequestDto quizDto) {
		Quiz quiz = findByIdOrThrow(quizDto.getId());
		Long categoryId = quizDto.getCategoryId();
		if (categoryId != null) {
			Category category = findCategoryOrThrow(categoryId);
			quiz.setCategory(category);
		}
		String description = quizDto.getDescription();
		if (description != null && !description.isBlank()) {
			quiz.setDescription(description);
		}
		String title = quizDto.getTitle();
		if (title != null && !title.isBlank()) {
			quiz.setTitle(title);
		}
		Boolean active = quizDto.getActive();
		if (active != null) {
			quiz.setActive(active);
		}

	}

	@Override
	public List<QuizDto> getAll() {
		return quizMapper.toDto(quizRepository.findAll());
	}


	private Category findCategoryOrThrow(long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() ->
				new NotFoundException("Категория с идентификатором " + categoryId + " не найдена"));
	}

	private Quiz findByIdOrThrow(long quizId) {
		return quizRepository.findById(quizId).orElseThrow(() ->
				new NotFoundException("Тест с идентификатором " + quizId + " не найден"));
	}
}
