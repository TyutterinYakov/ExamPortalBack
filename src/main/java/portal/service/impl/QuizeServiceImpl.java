package portal.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import portal.dao.ExamResultRepository;
import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.dao.UserRepository;
import portal.exception.UserNotFoundException;
import portal.model.Category;
import portal.model.ExamResult;
import portal.model.Question;
import portal.model.Quize;
import portal.model.User;
import portal.service.QuizeService;

@Service
public class QuizeServiceImpl implements QuizeService{

	@Autowired
	private QuizeRepository quizeDao;
	@Autowired
	private QuestionRepository questionDao;
	
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
		return quizeDao.findAll();
	}

	@Override
	public Quize getQuize(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findById(id);
		if(quizeOptional.isPresent()) {
			return quizeOptional.get();
		}
		return new Quize();
	}

	@Override
	public ResponseEntity<List<Quize>> getQuiziesOfCategory(Category ct) {
		List<Quize> quizies = new LinkedList<>();
		Optional<List<Quize>> listOptional = quizeDao.findAllByCategoryAndActive(ct, true);
		if(listOptional.isPresent()) {
			quizies = listOptional.get();
		}
		return ResponseEntity.ok(quizies);
	}

		
	
	
}
