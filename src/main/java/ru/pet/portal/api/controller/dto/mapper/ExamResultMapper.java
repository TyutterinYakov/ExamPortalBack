//package portal.api.dto.mapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import portal.api.dto.ExamResultDto;
//import portal.store.entity.ExamResult;
//
//@Component
//@RequiredArgsConstructor
//public class ExamResultMapper {
//
//	private final QuizeMapper quizeMapper;
//
//	public ExamResultDto toDto(ExamResult entity) {
//		return new ExamResultDto(
//				entity.getAnswerId(),
//				entity.getValidQuestion(),
//				entity.getInvalidQuestion(),
//				entity.getSkipQuestion(),
//				entity.getCountPoints(),
//				quizeMapper.toDto(entity.getQuize()),
//				entity.getQuestionsAndGivenAnswer());
//	}
//
//	public List<ExamResultDto> toDto(List<ExamResult> entities){
//		return entities
//				.stream()
//				.map(this::toDto)
//				.collect(Collectors.toList());
//	}
//
//}
