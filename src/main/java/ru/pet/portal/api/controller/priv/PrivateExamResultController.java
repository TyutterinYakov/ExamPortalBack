package ru.pet.portal.api.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultFullDto;
import ru.pet.portal.api.controller.dto.mapper.ExamMapper;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.api.service.model.AnswerExamRequest;
import ru.pet.portal.store.entity.UserE;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequestMapping("/api/exam/")
@RequiredArgsConstructor
public class PrivateExamResultController {

	private final ExamResultService examResultService;
	private final ExamMapper examMapper;

//	@GetMapping("{quizId}")
//	public void checkUserResultByQuizeId(@PathVariable("quizeId") Long id, Principal principal) {
//		examResultService.checkUserResultExam(principal.getName(), id);
//	}

	@PostMapping("submit/{quizId}")
	public ExamResultDto submitExam(@RequestBody List<AnswerExamRequestDto> answers,
									UsernamePasswordAuthenticationToken token,
									@PathVariable UUID quizId) {
		final List<AnswerExamRequest> examRequests = answers.stream().map(examMapper::toModel).toList();
		return examMapper.toDto(examResultService.submitExam(quizId, (UserE) token.getPrincipal(), examRequests));
	}

	@GetMapping
	public List<ExamResultFullDto> getAllByUser(UsernamePasswordAuthenticationToken token){
		final UserE userE = (UserE) token.getPrincipal();
		return examResultService.getAllUserResults(userE.getId()).stream().map(examMapper::toFullDto).toList();
	}
}
