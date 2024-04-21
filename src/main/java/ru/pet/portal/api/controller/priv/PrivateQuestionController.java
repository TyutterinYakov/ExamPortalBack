package ru.pet.portal.api.controller.priv;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.QuestionMapper;
import ru.pet.portal.api.controller.dto.question.QuestionResponseDto;
import ru.pet.portal.api.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/quizzes")
@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
public class PrivateQuestionController {

	private final QuestionService questionService;
    private final QuestionMapper questionMapper;

	@GetMapping("{quizId}/questions")
	public List<QuestionResponseDto> getByQuizIdAndActiveQuiz(@PathVariable UUID quizId){
		return questionService.getByQuizIdAndActiveQuiz(quizId).stream().map(questionMapper::toDto).toList();
	}


}
