package portal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.QuestionRepository;
import portal.dao.QuizeRepository;
import portal.exception.InvalidDataException;
import portal.exception.NotFoundException;
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
	public Question getQuestion(Long questionId) {
		return questionDao.findById(questionId).orElseThrow(()->
			new NotFoundException(String.format(
					"Вопрос с идентификатором \"%s\" не найден", questionId)
				)
		);
	}

	@Override
	public List<Question> getListQuestion() {
		return questionDao.findAll();
	}

	@Override
	public Question addQuestion(Question question) {
		findQuize(question.getQuize());
		countQuestionFromQuize(question.getQuize(), 1);
		return questionDao.saveAndFlush(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		findQuize(question.getQuize());
		return questionDao.save(question);
	}

	@Override
	public void removeQuestion(Long questionId) {
		Question question = getQuestion(questionId);
		questionDao.deleteById(question.getQuestionId());
		countQuestionFromQuize(question.getQuize(), -1);
	}
	
	@Override
	public Set<Question> getQuestionsOfQuize(Long quizeId) {
		Quize quize = quizeDao.findByQuizeIdAndActive(quizeId, true).orElseThrow(()->
				new NotFoundException(String.format(
						"Тестирование с идентификатором \"%s\"не найдено или закрыто", 
						quizeId))
				);
		return quize.getQuestions();
	}

	
	@Override
	public Set<Question> getAllQuestionsOfQuize(Long quizeId) {
		Quize quize = quizeDao.findById(quizeId).orElseThrow(()->
						new NotFoundException(
								String.format(
									"Тестирование с идентификатором \"%s\" не найдено", 
									quizeId
								)
							)
						);
		return quize.getQuestions();
	}

	
	@Transactional
	private void countQuestionFromQuize(Quize quize, int count) { //-1: delete 1: add
		Quize quizeOld = quizeDao.findById(quize.getQuizeId()).orElseThrow(()->
				new NotFoundException(
						String.format("Тестирование с идентификатором \"%s\" не найдено", 
								quize.getQuizeId()))
				);
		if(count==1) {
			quizeOld.setCountOfQuestion(quizeOld.getCountOfQuestion()+count);
		} else {
			quizeOld.setCountOfQuestion(quizeOld.getCountOfQuestion()+count);
		}
	}
	
	
	private Quize findQuize(Quize quize) {
		return quizeDao.findById(quize.getQuizeId()).orElseThrow(()->
		new NotFoundException(
				String.format("Тестирование с идентификатором \"%s\" не найдено", 
						quize.getQuizeId()))
		);
	}

}
