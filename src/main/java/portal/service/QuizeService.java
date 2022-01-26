package portal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import portal.exception.UserNotFoundException;
import portal.model.Category;
import portal.model.ExamResult;
import portal.model.Quize;


public interface QuizeService {
	public Quize addQuize(Quize quize);
	public Quize updateQuize(Quize quize);
	public void removeQuize(Long id);
	public List<Quize> listQuize();
	public Quize getQuize(Long id);
	public ResponseEntity<List<Quize>> getQuiziesOfCategory(Category ct);
	public ResponseEntity<List<ExamResult>> getAllResultFromQuize(Long id);
	public ResponseEntity<List<ExamResult>> checkUserResultExam(String name, Long id) throws UserNotFoundException;
	public void removeExamResult(Long id);
}
