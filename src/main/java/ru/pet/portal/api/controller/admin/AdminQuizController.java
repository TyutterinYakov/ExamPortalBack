//package portal.api.controller.admin;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import portal.api.controller.dto.QuizDto;
//import portal.api.controller.dto.QuizRequestDto;
//import portal.api.controller.dto.group.Create;
//import portal.api.controller.dto.group.Update;
//import portal.api.service.QuizService;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "${exam.portal.front-url}")
//@RequestMapping("/api/admin/categories")
//@RequiredArgsConstructor
//public class AdminQuizController {
//
//    private final QuizService quizService;
//
//    @PostMapping("quizzes")
//    public ResponseEntity<Void> add(@RequestBody @Validated(Create.class) QuizRequestDto quizDto) {
//        quizService.add(quizDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("quizzes/{quizId}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<Void> deleteById(@PathVariable long quizId) {
//        quizService.deleteById(quizId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//
//    @GetMapping("{categoryId}/quizzes")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<List<QuizDto>> getAllByCategory(@PathVariable("categoryId") long categoryId) {
//        return ResponseEntity.ok(quizService.getAllByCategory(categoryId));
//    }
//
//    @GetMapping("quizzes/{quizId}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<QuizDto> getById(@PathVariable Long quizId) {
//        return ResponseEntity.ok(quizService.getById(quizId));
//    }
//
//    @PutMapping("quizzes")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<QuizDto> update(@RequestBody @Validated(Update.class) QuizRequestDto quizDto) {
//        quizService.update(quizDto);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("quizzes")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<List<QuizDto>> getAll() {
//        return ResponseEntity.ok(quizService.getAll());
//    }
//
//
//}
