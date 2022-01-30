package portal.service;

import java.util.List;
import java.util.Set;

import portal.exception.InvalidDataException;
import portal.exception.NotPermissionException;
import portal.model.Question;

public interface QuestionService {
	
	public Question getQuestion(Long id);
	public List<Question> getListQuestion();
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public void removeQuestion(Long id);
	public Set<Question> getQuestionsOfQuize(Long quizeId) throws NotPermissionException,  InvalidDataException;
	public Set<Question> getAllQuestionsOfQuize(Long quizeId) throws InvalidDataException;
	
	
	
}
