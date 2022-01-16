package portal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.QuestionRepository;
import portal.model.Question;
import portal.model.Quize;
import portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	
	@Autowired
	private QuestionRepository questionDao;
	
	
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
		return questionDao.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionDao.save(question);
	}

	@Override
	public void removeQuestion(Long id) {
		questionDao.deleteById(id);
	}

	@Override
	public List<Question> getQuestionsOfQuize(Quize quize) {
		Optional<List<Question>> questionOpt =  questionDao.findAllByQuize(quize);
		if(questionOpt.isPresent()) {
			return questionOpt.get();
		}
		return null;
	}

}
