package portal.api.service;

import java.util.List;

import portal.store.entity.ExamResult;
import portal.store.entity.QuestionEntity;

public interface ExamResultService {
	public List<ExamResult> getAllResultFromQuize(Long id);
	public List<ExamResult> checkUserResultExam(String name, Long id);
	public void removeExamResult(Long id);
	public ExamResult getExamResult(String name, List<QuestionEntity> questions);
	public List<ExamResult> checkUserAllResultExam(String name);
}
