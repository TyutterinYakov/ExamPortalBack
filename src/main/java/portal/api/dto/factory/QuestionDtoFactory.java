package portal.api.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import portal.api.dto.QuestionDto;
import portal.store.entity.QuestionEntity;

@Component
public class QuestionDtoFactory {

	private final QuizeDtoFactory quizeDtoFactory;
	
	@Autowired
	public QuestionDtoFactory(QuizeDtoFactory quizeDtoFactory) {
		super();
		this.quizeDtoFactory = quizeDtoFactory;
	}



	public QuestionDto createQuestionDto(QuestionEntity entity) {
		return new QuestionDto(
				entity.getQuestionId(), 
				entity.getContent(), 
				entity.getOption1(), 
				entity.getOption2(), 
				entity.getOption3(), 
				entity.getOption4(), 
				quizeDtoFactory
					.createQuizeDto(
							entity.getQuize())
				);
	}
	
	public List<QuestionDto> createListQuestionDto(List<QuestionEntity> entities){
		return entities
				.stream()
				.map(this::createQuestionDto)
			  .collect(Collectors.toList());
	}
}
