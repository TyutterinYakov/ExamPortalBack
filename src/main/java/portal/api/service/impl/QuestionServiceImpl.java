package portal.api.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.api.dto.QuestionDto;
import portal.api.dto.factory.QuestionDtoFactory;
import portal.api.exception.BadRequestException;
import portal.api.exception.NotFoundException;
import portal.api.service.QuestionService;
import portal.store.entity.QuestionEntity;
import portal.store.entity.QuizeEntity;
import portal.store.repository.QuestionRepository;
import portal.store.repository.QuizeRepository;

@Service
public class QuestionServiceImpl implements QuestionService{

	
	private QuestionRepository questionDao;
	private QuizeRepository quizeDao;
	private final QuestionDtoFactory questionDtoFactory;
	
	@Autowired
	public QuestionServiceImpl(QuestionRepository questionDao, QuizeRepository quizeDao,
			QuestionDtoFactory questionDtoFactory) {
		super();
		this.questionDao = questionDao;
		this.quizeDao = quizeDao;
		this.questionDtoFactory = questionDtoFactory;
	}

	//WRITE
	
	@Override
	public QuestionEntity addQuestion(QuestionEntity question) {
		findQuize(question.getQuize());
		countQuestionFromQuize(question.getQuize(), 1);
		return questionDao.save(question);
	}

	@Override
	public QuestionEntity updateQuestion(QuestionEntity question) {
		findQuize(question.getQuize());
		return questionDao.save(question);
	}
	
	@Override
	public void deleteQuestionById(Long questionId) {
		QuestionEntity question = getQuestionAnyById(questionId);
		countQuestionFromQuize(question.getQuize(), -1);
		questionDao.deleteById(question.getQuestionId());
		
	}
	
	@Override
	public QuestionEntity getQuestionAnyById(Long questionId) {
		if(questionId==0||questionId==null) {
			throw new BadRequestException("questionId не может быть равно 0/null");
		}
		return questionDao.findById(questionId).orElseThrow(()->
			new NotFoundException(String.format(
					"Вопрос с идентификатором \"%s\" не найден", questionId)
				)
		);
	}
	
	
	@Override
	public List<QuestionEntity> getAllQuestionsOfAnyQuize(Long quizeId) {
		QuizeEntity quize = quizeDao.findById(quizeId).orElseThrow(()->
						new NotFoundException(
								String.format(
									"Тестирование с идентификатором \"%s\" не найдено", 
									quizeId
								)
							)
						);
		return quize.getQuestions();
	}
	
	@Override
	public List<QuestionEntity> getAllAnyQuestion() {
		return questionDao.findAll();
	}
	
	
	private QuizeEntity findQuize(QuizeEntity quize) {
		return quizeDao.findById(quize.getQuizeId()).orElseThrow(()->
		new NotFoundException(
				String.format("Тестирование с идентификатором \"%s\" не найдено", 
						quize.getQuizeId()))
		);
	}
	
	@Transactional
	private void countQuestionFromQuize(QuizeEntity quize, int count) { //-1: delete 1: add
		QuizeEntity quizeOld = quizeDao.findById(quize.getQuizeId()).orElseThrow(()->
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
	
	//READ
	
	@Override
	public List<QuestionDto> getQuestionsOfQuize(Long quizeId) {
		QuizeEntity quize = quizeDao.findByQuizeIdAndActive(quizeId, true).orElseThrow(()->
				new NotFoundException(String.format(
						"Тестирование с идентификатором \"%s\"не найдено или закрыто", 
						quizeId))
				);
		return questionDtoFactory.createListQuestionDto(quize.getQuestions());
	}


	
	

	

}
