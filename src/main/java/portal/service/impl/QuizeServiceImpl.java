package portal.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.CategoryRepository;
import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.exception.NotFoundException;
import portal.model.Category;
import portal.model.Question;
import portal.model.Quize;
import portal.service.QuizeService;

@Service
public class QuizeServiceImpl implements QuizeService{

	private QuizeRepository quizeDao;
	private CategoryRepository categoryDao;
	
	@Autowired
	public QuizeServiceImpl(QuizeRepository quizeDao, CategoryRepository categoryDao) {
		super();
		this.quizeDao = quizeDao;
		this.categoryDao = categoryDao;
	}

	@Override
	public Quize addQuize(Quize quize) {
		return quizeDao.save(quize);
	}

	@Override
	public Quize updateQuize(Quize quize) {
		getQuizeAdmin(quize.getQuizeId());
		return quizeDao.save(quize);
	}

	@Override
	public void removeQuize(Long quizeId) {
			quizeDao.delete(getQuizeAdmin(quizeId));
	}

	@Override
	public List<Quize> listQuize() {
		return quizeDao.findAllByActive(true);
	}
	
	@Override
	public List<Quize> listQuizeAny() {
		return quizeDao.findAll();
	}

	@Override
	public Quize getQuize(Long quizeId) {
		return quizeDao.findByActiveAndQuizeId(true, quizeId).orElseThrow(()->
				new NotFoundException(
						String.format(
								"Тест с идентификатором \"%s\" не найден или недоступен",
								quizeId)
					)
				);
	}

	@Override
	public List<Quize> getQuiziesOfCategory(Long categoryId) {
		Category category = categoryDao.findById(categoryId).orElseThrow(()->
						new NotFoundException(
								String.format(
									"Категория с идентификатором \"%s\" не найдена",
									categoryId))
						);
		return category
				.getQuizies()
					.stream()
					.filter((q)->
						q.isActive())
				.collect(Collectors.toList());
	}
	@Override
	public List<Quize> getQuiziesOfCategoryAll(Long categoryId) {
		Optional<Category> category = categoryDao.findById(categoryId);
		List<Quize> quizies = new LinkedList<>();
		
		if(category.isPresent()) {
			category.get().getQuizies();
		}
		return quizies;
	}

	@Override
	public Quize getQuizeAdmin(Long quizeId) {
		return quizeDao.findById(quizeId).orElseThrow(()->
						new NotFoundException(String.format(
							"Тест с идентификатором \"%s\" не найден", 
							quizeId)
						)
				);
	}
	

		
	
	
}
