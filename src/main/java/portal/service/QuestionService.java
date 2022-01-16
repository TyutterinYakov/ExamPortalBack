package portal.service;

import java.util.List;

import portal.model.Question;
import portal.model.Quize;

public interface QuestionService {
	
	public Question getQuestion(Long id);
	public List<Question> getListQuestion();
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public void removeQuestion(Long id);
	public List<Question> getQuestionsOfQuize(Quize quize);
	
	
}
