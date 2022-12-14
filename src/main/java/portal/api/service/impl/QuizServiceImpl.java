package portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import portal.api.dto.QuizRequestDto;
import portal.api.dto.mapper.QuizMapper;
import portal.api.exception.NotFoundException;
import portal.api.service.QuizeService;
import portal.store.entity.Category;
import portal.store.entity.Quiz;
import portal.store.repository.CategoryRepository;
import portal.store.repository.QuizRepository;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizeService{

	private final CategoryRepository categoryRepository;
	private final QuizRepository quizRepository;
	private final QuizMapper quizMapper;

	@Override
	public void add(QuizRequestDto quizDto) {
		Category category = categoryRepository.findById(quizDto.getCategoryId()).orElseThrow(() ->
				new NotFoundException("Категория с идентификатором " + quizDto.getCategoryId() + " не найдена"));
		Quiz quiz = quizMapper.toEntity(quizDto, category);
		quizRepository.save(quiz);
	}

	@Override
	public void deleteById(long quizId) {
		quizRepository.findById(quizId).ifPresent(
				quiz -> quizRepository.deleteById(quizId)
		);
	}

//	@Override
//	public QuizeDto updateQuize(QuizeDto quizeDto) {
//		getQuizeEntityAny(quizeDto.getQuizeId());
//		return quizeDtoFactory
//				.toDto(
//						quizeDao.saveAndFlush(
//								new QuizeEntity().makeQuizeDtoTo(
//										quizeDto,
//										categoryService.getCategoryEntityById(quizeDto.
//												getCategoryDto()
//												.getCategoryId())
//								)
//						)
//				);
//	}
//
//	@Override
//	public void deleteQuizeById(Long quizeId) {
//		quizeDao.deleteById(getQuizeEntityAny(quizeId).getQuizeId());
//	}
//
//	@Override
//	public QuizeDto getQuizeAny(Long quizeId) {
//		return quizeDtoFactory.toDto(quizeDao.findById(quizeId).orElseThrow(()->
//						new NotFoundException(String.format(
//							"Тест с идентификатором \"%s\" не найден",
//							quizeId)
//						)
//				));
//	}
//
//	@Override
//	public List<QuizeDto> getAnyQuizies() {
//		return quizeDtoFactory.toDto(quizeDao.findAll());
//	}
//
//	@Override
//	public List<QuizeDto> getAnyQuiziesByCategoryId(Long categoryId) {
//		return quizeDtoFactory.toDto(
//				findCategoryById(categoryId)
//				.getQuizies());
//	}
//
//	private QuizeEntity getQuizeEntityAny(Long quizeId) {
//		if(quizeId==0||quizeId==null) {
//			throw new BadRequestException("Поле quizeId равно null или 0");
//		}
//		return quizeDao.findById(quizeId).orElseThrow(()->
//						new NotFoundException(String.format(
//							"Тест с идентификатором \"%s\" не найден",
//							quizeId)
//						)
//				);
//	}
//
//
//	//READ
//
//	@Override
//	public List<QuizeDto> getActiveQuiziesByCategoryId(Long categoryId) {
//		CategoryEntity category = categoryDao.findById(categoryId).orElseThrow(()->
//		new NotFoundException(
//				String.format(
//					"Категория с идентификатором \"%s\" не найдена",
//					categoryId))
//		);
//		return quizeDtoFactory.toDto(category
//													.getQuizies().stream()
//													.filter((q)->q.isActive())
//													.collect(Collectors.toList()));
//	}
//
//	@Override
//	public List<QuizeDto> getActiveQuizies() {
//		return quizeDtoFactory.toDto(quizeDao.findAllByActive(true));
//	}
//
//	@Override
//	public QuizeDto getActiveQuize(Long quizeId) {
//		return quizeDtoFactory.toDto(quizeDao.findByActiveAndQuizeId(true, quizeId).orElseThrow(()->
//		new NotFoundException(
//				String.format(
//						"Тест с идентификатором \"%s\" не найден или недоступен",
//						quizeId)
//			)
//		));
//	}
//
//
//	private CategoryEntity findCategoryById(Long categoryId) {
//		return categoryDao.findById(categoryId).orElseThrow(()->
//			new NotFoundException(
//				String.format(
//					"Категория с идентификатором \"%s\" не найдена",
//					categoryId))
//			);
//	}





}
