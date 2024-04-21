package ru.pet.portal.api.controller.admin;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.QuizMapper;
import ru.pet.portal.api.controller.dto.quiz.QuizRequestDto;
import ru.pet.portal.api.controller.dto.quiz.QuizResponseDto;
import ru.pet.portal.api.controller.dto.validation.group.Create;
import ru.pet.portal.api.controller.dto.validation.group.Update;
import ru.pet.portal.api.service.QuizService;
import ru.pet.portal.store.entity.Quiz;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @PostMapping("quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Validated(Create.class) QuizRequestDto quizDto) {
        final Quiz quiz = quizMapper.toEntity(quizDto);
        quizService.create(quizDto.getCategoryId(), quiz);
    }

    @DeleteMapping("quizzes/{quizId}")
    public void deleteById(@PathVariable String quizId) {
        quizService.deleteById(UUID.fromString(quizId));
    }


    @GetMapping("{categoryId}/quizzes")
    public List<QuizResponseDto> getAllByCategory(@PathVariable("categoryId") UUID categoryId,
                                                  @RequestParam int from,
                                                  @RequestParam int size) {
        final List<Quiz> quizzes = quizService.getAllByCategoryId(categoryId, from, size);
        return quizzes.stream().map(quizMapper::toDto).toList();
    }

    @GetMapping("quizzes/{quizId}")
    public QuizResponseDto getById(@PathVariable UUID quizId) {
        return quizMapper.toDto(quizService.getById(quizId));
    }

    @PutMapping("quizzes/{quizId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody @Validated(Update.class) QuizRequestDto quizDto,
                       @PathVariable("quizId") UUID quizId) {
        quizService.update(quizDto.getCategoryId(), quizMapper.toEntity(quizDto), quizId);
    }

    @GetMapping("quizzes")
    public List<QuizResponseDto> getAll(@RequestParam(defaultValue = "0") @Min(0) int from,
                                @RequestParam(defaultValue = "10") @Min(1) int size) {
        return quizService.getAll(from, size).stream().map(quizMapper::toDto).toList();
    }


}
