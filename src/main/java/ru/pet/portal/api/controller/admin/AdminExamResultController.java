//package ru.pet.portal.api.controller.admin;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.pet.portal.api.controller.dto.result.ExamResultDto;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@CrossOrigin(origins = "${front.url}")
//@RequestMapping("/api/exam/result")
//@RequiredArgsConstructor
//public class AdminExamResultController {
//
//    private final ExamResultService examResultService;
//
//    @GetMapping("{quizId}")
//    public List<ExamResultDto> getAllByQuizeId(@PathVariable UUID ){
//        return ResponseEntity.ok(examResultService.getAllResultFromQuize(id));
//    }
//
//    @DeleteMapping("{answerId}")
//    public ResponseEntity<?> deleteByAnswerId(@PathVariable("answerId") Long id){
//        examResultService.removeExamResult(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//}
