package portal.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.CategoryRepository;
import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.model.Category;
import portal.model.Question;
import portal.model.Quize;
import portal.service.QuizeService;

@Service
public class QuizeServiceImpl implements QuizeService{

	private QuizeRepository quizeDao;
	private QuestionRepository questionDao;
	private CategoryRepository categoryDao;
	
	@Autowired
	public QuizeServiceImpl(QuizeRepository quizeDao, QuestionRepository questionDao, CategoryRepository categoryDao) {
		super();
		this.quizeDao = quizeDao;
		this.questionDao = questionDao;
		this.categoryDao = categoryDao;
	}

	@Override
	public Quize addQuize(Quize quize) {
		quize.setCountOfQuestion(0); 
		return quizeDao.save(quize);
	}

	@Override
	public Quize updateQuize(Quize quize) {
		Optional<List<Question>> question = questionDao.findAllByQuize(quize);
		if(question.isPresent()) {
			quize.setCountOfQuestion(question.get().size());  //Подсчет вопросов при обновлении теста
		} else {
			quize.setCountOfQuestion(0);
		}
		return quizeDao.save(quize);
	}

	@Override
	public void removeQuize(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findById(id);
		if(quizeOptional.isPresent()) {
			quizeDao.delete(quizeOptional.get());
		}
		
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
	public Quize getQuize(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findByActiveAndQuizeId(true, id);
		if(quizeOptional.isPresent()) {
			return quizeOptional.get();
		}
		return new Quize();
	}

	@Override
	public List<Quize> getQuiziesOfCategory(Long categoryId) {
		Category category = new Category();
		category.setCategoryId(categoryId);
		List<Quize> quizies = new LinkedList<>();
		Optional<List<Quize>> listOptional = quizeDao.findAllByCategoryAndActive(category, true);
		if(listOptional.isPresent()) {
			quizies = listOptional.get();
		}
		return quizies;
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
	public Quize getQuizeAdmin(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findById(id);
		if(quizeOptional.isPresent()) {
			return quizeOptional.get();
		}
		return new Quize();
	}
	

		
	
	
}
