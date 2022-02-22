package portal.api.service;

import java.util.List;

import portal.api.dto.ExamResultDto;
import portal.api.dto.QuestionDto;
import portal.store.entity.ExamResultEntity;

public interface ExamResultService {
	
	//WRITE
	public void removeExamResult(Long id);
	public List<ExamResultEntity> getAllResultFromQuize(Long id);
	
	
	//READ
	public List<ExamResultDto> checkUserResultExam(String name, Long id);
	public ExamResultDto getExamResult(String name, List<QuestionDto> questions);
	public List<ExamResultDto> checkUserAllResultExam(String name);
}
