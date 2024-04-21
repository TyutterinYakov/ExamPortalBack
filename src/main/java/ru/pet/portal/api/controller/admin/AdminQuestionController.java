package ru.pet.portal.api.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.QuestionMapper;
import ru.pet.portal.api.controller.dto.question.QuestionAdminRequestDto;
import ru.pet.portal.api.controller.dto.question.QuestionAdminResponseDto;
import ru.pet.portal.api.service.QuestionService;
import ru.pet.portal.store.entity.Question;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/admin/quizzes")
@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
@Validated
public class AdminQuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("questions/{questionId}")
    public QuestionAdminResponseDto getById(@PathVariable("questionId") UUID questionId) {
        final Question question = questionService.getById(questionId);
        return questionMapper.toAdminDto(question);
    }

    @PostMapping("questions")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid QuestionAdminRequestDto question) {
        questionService.create(question.getQuizId(), questionMapper.toEntity(question));
    }

    @PutMapping("questions/{questionId}")
    public void update(@RequestBody @Valid QuestionAdminRequestDto question,
                       @PathVariable UUID questionId) {
        questionService.update(questionId, questionMapper.toEntity(question));
    }

    @DeleteMapping("questions/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("questionId") UUID questionId) {
        questionService.deleteById(questionId);
    }

    @GetMapping("{quizId}/questions")
    public List<QuestionAdminResponseDto> getAllByQuizId(@PathVariable("quizId") UUID quizId,
                                                 @RequestParam(defaultValue = "0") @Min(0) int from,
                                                 @RequestParam(defaultValue = "10") @Min(1) int size) {
        return questionService.getAllByQuizId(quizId, from, size).stream()
                .map(questionMapper::toAdminDto).toList();
    }
}
