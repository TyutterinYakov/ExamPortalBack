//package portal.api.dto.mapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import portal.api.dto.QuestionDto;
//import portal.store.entity.Question;
//
//@Component
//@RequiredArgsConstructor
//public class QuestionMapper {
//
//	private final QuizeMapper quizeMapper;
//
//	public QuestionDto toDto(Question entity) {
//		return new QuestionDto(
//				entity.getQuestionId(),
//				entity.getContent(),
//				entity.getOption1(),
//				entity.getOption2(),
//				entity.getOption3(),
//				entity.getOption4(),
//				quizeMapper.toDto(entity.getQuize()));
//	}
//
//	public List<QuestionDto> toDto(List<Question> entities){
//		return entities.stream().map(this::toDto).collect(Collectors.toList());
//	}
//
//}
