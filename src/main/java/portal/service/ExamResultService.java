package portal.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import portal.exception.InvalidDataException;
import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;

public interface ExamResultService {
	public List<ExamResult> getAllResultFromQuize(Long id);
	public List<ExamResult> checkUserResultExam(String name, Long id) throws UserNotFoundException;
	public void removeExamResult(Long id);
	public ExamResult getExamResult(String name, List<Question> questions) throws UserNotFoundException, InvalidDataException;
	public List<ExamResult> checkUserAllResultExam(String name) throws UserNotFoundException;
}
