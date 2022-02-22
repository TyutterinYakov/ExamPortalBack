package portal.api.service;

import java.util.List;

import portal.api.dto.QuestionDto;
import portal.store.entity.QuestionEntity;

public interface QuestionService {
	//WRITE
	public List<QuestionEntity> getAllQuestionsOfAnyQuize(Long quizeId); //
	public QuestionEntity addQuestion(QuestionEntity question); //
	public QuestionEntity updateQuestion(QuestionEntity question); //
	public void deleteQuestionById(Long questionId); //
	public List<QuestionEntity> getAllAnyQuestion(); //
	public QuestionEntity getQuestionAnyById(Long questionId); 
	
	//READ
	public List<QuestionDto> getQuestionsOfQuize(Long quizeId) ;
	
	
	
	
}
