package portal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.exception.InvalidDataException;
import portal.exception.NotPermissionException;
import portal.model.Question;
import portal.model.Quize;
import portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	
	private QuestionRepository questionDao;
	private QuizeRepository quizeDao;
	
	@Autowired
	public QuestionServiceImpl(QuestionRepository questionDao, QuizeRepository quizeDao) {
		super();
		this.questionDao = questionDao;
		this.quizeDao = quizeDao;
	}

	@Override
	public Question getQuestion(Long id) {
		Optional<Question> questionOpt = questionDao.findById(id);
		if(questionOpt.isPresent()) {
			return questionOpt.get();
		}
		return null;
	}

	@Override
	public List<Question> getListQuestion() {
		return questionDao.findAll();
	}

	@Override
	public Question addQuestion(Question question) {
		countQuestionFromQuize(question.getQuize(), 1);
		return questionDao.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		countQuestionFromQuize(question.getQuize(), 0);
		return questionDao.save(question);
	}

	@Override
	public void removeQuestion(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findById(id);
		if(quizeOptional.isPresent()) {
			countQuestionFromQuize(quizeOptional.get(), -1);
			questionDao.deleteById(id);
		}
	}

	private void countQuestionFromQuize(Quize quize, int x) { //x -----  o - update, 1 - add, -1 - remove
			Optional<List<Question>> questions = questionDao.findAllByQuize(quize);
			if(questions.isPresent()) {
				quize.setCountOfQuestion(questions.get().size()+x);
				quizeDao.save(quize);
			}
	}
	
	
	@Override
	public Set<Question> getQuestionsOfQuize(Long quizeId) throws NotPermissionException, InvalidDataException {
		Optional<Quize> quizeOpt = quizeDao.findById(quizeId);
		if(quizeOpt.isPresent()) {
			if(!quizeOpt.get().isActive()) {
				throw new NotPermissionException();
			}
			return quizeOpt.get().getQuestions();
		}
		throw new InvalidDataException();
	}

	@Override
	public Set<Question> getAllQuestionsOfQuize(Long quizeId) throws InvalidDataException {
		Optional<Quize> quizeOpt = quizeDao.findById(quizeId);
		if(quizeOpt.isPresent()) {
			return quizeOpt.get().getQuestions();
		}
		throw new InvalidDataException();
	}


}
