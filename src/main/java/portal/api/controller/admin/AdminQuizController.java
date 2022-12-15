package portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import portal.api.dto.QuizDto;
import portal.api.dto.QuizRequestDto;
import portal.api.service.QuizService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizService quizService;

    @PostMapping("quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> add(@RequestBody @Valid QuizRequestDto quizDto) {
        quizService.add(quizDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("quizzes/{quizId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> deleteById(@PathVariable long quizId) {
        quizService.deleteById(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("{categoryId}/quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<List<QuizDto>> getAllByCategory(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(quizService.getAllByCategory(categoryId));
    }

    @GetMapping("quizzes/{quizId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<QuizDto> getById(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getById(quizId));
    }

    @PutMapping("quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<QuizDto> update(@RequestBody @Valid QuizRequestDto quizDto) {
        quizService.update(quizDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<List<QuizDto>> getAll() {
        return ResponseEntity.ok(quizService.getAll());
    }


}
