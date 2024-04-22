package ru.pet.portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.exam.ExamResultFullDto;
import ru.pet.portal.api.controller.dto.mapper.ExamMapper;
import ru.pet.portal.api.service.ExamResultService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequestMapping("/api/admin/exam/")
@RequiredArgsConstructor
public class AdminExamResultController {

    private final ExamResultService examResultService;
    private final ExamMapper examMapper;

    @GetMapping("{quizId}")
    public List<ExamResultFullDto> getAllByQuizId(@PathVariable UUID quizId){
        return examResultService.getAllByQuizId(quizId).stream().map(examMapper::toFullDto).toList();
    }

    @DeleteMapping("{resultId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAnswerId(@PathVariable UUID resultId){
        examResultService.deleteById(resultId);
    }
}
