package portal.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import portal.dao.ExamResultRepository;
import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.dao.UserRepository;
import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;
import portal.model.Quize;
import portal.model.User;
import portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	
	@Autowired
	private QuestionRepository questionDao;
	@Autowired
	private QuizeRepository quizeDao;
	@Autowired
	private ExamResultRepository examDao;
	@Autowired
	private UserRepository userDao;
	
	
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
	public List<Question> getQuestionsOfQuize(Long quizeId) {
		Optional<Quize> quizeOpt = quizeDao.findById(quizeId);
		if(quizeOpt.isPresent()) {
		Optional<List<Question>> questionOpt =  questionDao.findAllByQuize(quizeOpt.get());
		if(questionOpt.isPresent()) {
			return questionOpt.get();
		}
		}
		return new LinkedList<Question>();
	}


}
