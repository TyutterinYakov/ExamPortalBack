package ru.pet.portal.api.controller.priv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.answer.AnswerExamRequestDto;
import ru.pet.portal.api.controller.dto.exam.ExamResultDto;
import ru.pet.portal.api.controller.dto.mapper.ExamMapper;
import ru.pet.portal.api.service.ExamResultService;
import ru.pet.portal.store.entity.User;

import java.security.Principal;
import java.util.List;

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

	@PostMapping("submit")
	public ExamResultDto submitExam(@RequestBody List<AnswerExamRequestDto> answers, UsernamePasswordAuthenticationToken token) {
		final User user = (User) token.getPrincipal();
		return examMapper.toDto(examResultService.submitExam(user.getId(), answers));
	}

//	@GetMapping("all")
//	public void getAllResultsExamsByUser(Principal principal){
//		examResultService.checkUserAllResultExam(principal.getName());
//	}
}
