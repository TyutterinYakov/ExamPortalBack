package portal.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.api.dto.QuizeDto;
import portal.api.dto.factory.QuizeDtoFactory;
import portal.api.exception.BadRequestException;
import portal.api.exception.NotFoundException;
import portal.api.service.CategoryService;
import portal.api.service.QuizeService;
import portal.store.entity.CategoryEntity;
import portal.store.entity.QuizeEntity;
import portal.store.repository.CategoryRepository;
import portal.store.repository.QuizeRepository;

@Service
public class QuizeServiceImpl implements QuizeService{

	private final QuizeRepository quizeDao;
	private final CategoryRepository categoryDao;
	private final QuizeDtoFactory quizeDtoFactory;
	private final CategoryService categoryService;
	
	@Autowired
	public QuizeServiceImpl(QuizeRepository quizeDao, CategoryRepository categoryDao, QuizeDtoFactory quizeDtoFactory,
			CategoryService categoryService) {
		super();
		this.quizeDao = quizeDao;
		this.categoryDao = categoryDao;
		this.quizeDtoFactory = quizeDtoFactory;
		this.categoryService = categoryService;
	}

	//WRITE

	@Override
	public QuizeDto addQuize(QuizeDto quizeDto) {
		return quizeDtoFactory
				.createQuizeDto(
						quizeDao.saveAndFlush(
								new QuizeEntity().makeQuizeDtoTo(
										quizeDto,
										categoryService.getCategoryEntityById(quizeDto
												.getCategoryDto()
												.getCategoryId())
								)
						)
				);
	}
	
	@Override
	public QuizeDto updateQuize(QuizeDto quizeDto) {
		getQuizeEntityAny(quizeDto.getQuizeId());
		return quizeDtoFactory
				.createQuizeDto(
						quizeDao.saveAndFlush(
								new QuizeEntity().makeQuizeDtoTo(
										quizeDto,
										categoryService.getCategoryEntityById(quizeDto.
												getCategoryDto()
												.getCategoryId())
								)
						)
				);
	}

	@Override
	public void deleteQuizeById(Long quizeId) {
		quizeDao.deleteById(getQuizeEntityAny(quizeId).getQuizeId());
	}
	
	@Override
	public QuizeDto getQuizeAny(Long quizeId) {
		return quizeDtoFactory.createQuizeDto(quizeDao.findById(quizeId).orElseThrow(()->
						new NotFoundException(String.format(
							"Тест с идентификатором \"%s\" не найден", 
							quizeId)
						)
				));
	}
	
	@Override
	public List<QuizeDto> getAnyQuizies() {
		return quizeDtoFactory.createListQuizeDto(quizeDao.findAll());
	}
	
	@Override
	public List<QuizeDto> getAnyQuiziesByCategoryId(Long categoryId) {
		return quizeDtoFactory.createListQuizeDto(
				findCategoryById(categoryId)
				.getQuizies());
	}
	
	private QuizeEntity getQuizeEntityAny(Long quizeId) {
		if(quizeId==0||quizeId==null) {
			throw new BadRequestException("Поле quizeId равно null или 0");
		}
		return quizeDao.findById(quizeId).orElseThrow(()->
						new NotFoundException(String.format(
							"Тест с идентификатором \"%s\" не найден", 
							quizeId)
						)
				);
	}
	
	
	//READ
	
	@Override
	public List<QuizeDto> getActiveQuiziesByCategoryId(Long categoryId) {
		CategoryEntity category = categoryDao.findById(categoryId).orElseThrow(()->
		new NotFoundException(
				String.format(
					"Категория с идентификатором \"%s\" не найдена",
					categoryId))
		);
		return quizeDtoFactory.createListQuizeDto(category
													.getQuizies().stream()
													.filter((q)->q.isActive())
													.collect(Collectors.toList()));
	}
	
	@Override
	public List<QuizeDto> getActiveQuizies() {
		return quizeDtoFactory.createListQuizeDto(quizeDao.findAllByActive(true));
	}

	@Override
	public QuizeDto getActiveQuize(Long quizeId) {
		return quizeDtoFactory.createQuizeDto(quizeDao.findByActiveAndQuizeId(true, quizeId).orElseThrow(()->
		new NotFoundException(
				String.format(
						"Тест с идентификатором \"%s\" не найден или недоступен",
						quizeId)
			)
		));
	}


	private CategoryEntity findCategoryById(Long categoryId) {
		return categoryDao.findById(categoryId).orElseThrow(()->
			new NotFoundException(
				String.format(
					"Категория с идентификатором \"%s\" не найдена",
					categoryId))
			);
	}
	

		
	
	
}
