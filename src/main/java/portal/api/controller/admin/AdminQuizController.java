package portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import portal.api.dto.QuizRequestDto;
import portal.api.service.QuizeService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/categories")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizeService quizeService;

    @PostMapping("quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> add(@RequestBody @Valid QuizRequestDto quizDto) {
        quizeService.add(quizDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("quizzes/{quizId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> deleteById(@PathVariable long quizId) {
        quizeService.deleteById(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("{categoryId}/quizzes/any")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<List<QuizeDto>> getAllByCategoryId(@PathVariable("categoryId") Long categoryId)
    {
        return ResponseEntity.ok(quizeService.getAnyQuiziesByCategoryId(categoryId));
    }


    @GetMapping("quizzes/{quizId}/any")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<QuizeDto> getAnyQuize(@PathVariable("quizeId") Long quizeId) {
        return ResponseEntity.ok(quizeService.getQuizeAny(quizeId));
    }

    @PutMapping("quizzes")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<QuizeDto> update(@RequestBody @Valid QuizeDto quizeDto) {
        return ResponseEntity.ok(quizeService.updateQuize(quizeDto));
    }


    @GetMapping("quizzes/any")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<List<QuizeDto>> getAnyQuizies() {
        return ResponseEntity.ok(quizeService.getAnyQuizies());
    }


}
