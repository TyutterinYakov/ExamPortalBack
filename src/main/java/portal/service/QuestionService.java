package portal.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;

public interface QuestionService {
	
	public Question getQuestion(Long id);
	public List<Question> getListQuestion();
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public void removeQuestion(Long id);
	public List<Question> getQuestionsOfQuize(Long quizeId);
	public ExamResult getExamResult(String name, List<Question> questions) throws UserNotFoundException, NotFoundException;
	
	
	
}
