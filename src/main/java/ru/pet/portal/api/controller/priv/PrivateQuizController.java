package ru.pet.portal.api.controller.priv;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.QuizMapper;
import ru.pet.portal.api.controller.dto.quiz.QuizResponseDto;
import ru.pet.portal.api.service.QuizService;
import ru.pet.portal.store.entity.QuizE;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class PrivateQuizController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @GetMapping("quizzes/{quizId}")
    public QuizResponseDto getByIdAndActive(@PathVariable UUID quizId) {
        final QuizE quiz = quizService.getByIdAndActive(quizId);
        return quizMapper.toDto(quiz);
    }

    @GetMapping("quizzes")
    public List<QuizResponseDto> getAllByActive(@Min(0) @RequestParam(defaultValue = "0") int from,
                                                @Min(1) @RequestParam(defaultValue = "10") int size) {
        return quizService.getAllByActive(from, size).stream().map(quizMapper::toDto).toList();
    }

    @GetMapping("{categoryId}/quizzes")
    public List<QuizResponseDto> getAllByCategoryIdAndActive(@Min(0) @RequestParam(defaultValue = "0") int from,
                                                             @Min(1) @RequestParam(defaultValue = "10") int size,
                                                             @PathVariable("categoryId") UUID categoryId) {
        return quizService.getActiveQuizzesByCategoryId(categoryId, from, size).stream().map(quizMapper::toDto).toList();
    }

}
