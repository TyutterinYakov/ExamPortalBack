//package portal.api.controller.admin;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import portal.api.service.QuestionService;
//import portal.store.entity.Question;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequestMapping("/api/quizies")
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//@RequiredArgsConstructor
//public class AdminQuestionController {
//
//    private final QuestionService questionService;
//
//
//    @GetMapping("questions/{questionId}/any")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<Question> getById(@PathVariable("questionId") Long questionId){
//        return ResponseEntity.ok(questionService.getQuestionAnyById(questionId));
//    }
//
//    @GetMapping("questions/any")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<List<Question>> getAll(){
//        return ResponseEntity.ok(questionService.getAllAnyQuestion());
//    }
//
//    @PostMapping("questions")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<Question> add(@RequestBody @Valid Question question){
//        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
//    }
//
//    @PutMapping("questions")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<Question> update(@RequestBody @Valid Question question){
//        return ResponseEntity.ok(questionService.updateQuestion(question));
//    }
//
//    @DeleteMapping("questions/{questionId}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<?> deleteById(@PathVariable("questionId") Long questionId) {
//        questionService.deleteQuestionById(questionId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("{quizeId}/questions/any")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<List<Question>> getByQuizeId(@PathVariable("quizeId") Long quizeId){
//        return ResponseEntity.ok(questionService.getAllQuestionsOfAnyQuize(quizeId));
//    }
//}
