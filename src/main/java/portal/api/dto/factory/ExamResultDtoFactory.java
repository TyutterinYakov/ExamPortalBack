package portal.api.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import portal.api.dto.ExamResultDto;
import portal.store.entity.ExamResultEntity;

@Component
public class ExamResultDtoFactory {

	private final QuizeDtoFactory quizeDtoFactory;
	
	@Autowired
	public ExamResultDtoFactory(QuizeDtoFactory quizeDtoFactory) {
		super();
		this.quizeDtoFactory = quizeDtoFactory;
	}



	public ExamResultDto createExamResultDto(ExamResultEntity entity) {
		return new ExamResultDto(
				entity.getAnswerId(),
				entity.getValidQuestion(),
				entity.getInvalidQuestion(),
				entity.getSkipQuestion(),
				entity.getCountPoints(),
				quizeDtoFactory.createQuizeDto(
						entity.getQuize()),
				entity.getQuestionsAndGivenAnswer()
				);
	}
	
	public List<ExamResultDto> createListExamResultDto(List<ExamResultEntity> entities){
		return entities
				.stream()
				.map(this::createExamResultDto)
				.collect(Collectors.toList());
	}
}
