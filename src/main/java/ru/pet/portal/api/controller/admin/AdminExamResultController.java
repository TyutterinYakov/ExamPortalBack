//package portal.api.controller.admin;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import portal.api.service.ExamResultService;
//import portal.store.entity.ExamResult;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "${front.url}")
//@RequestMapping("/api/exam/result")
//@RequiredArgsConstructor
//public class AdminExamResultController {
//
//    private final ExamResultService examResultService;
//
//    @GetMapping("{quizeId}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<List<ExamResult>> getAllByQuizeId(@PathVariable("quizeId") Long id){
//        return ResponseEntity.ok(examResultService.getAllResultFromQuize(id));
//    }
//
//    @DeleteMapping("{answerId}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public ResponseEntity<?> deleteByAnswerId(@PathVariable("answerId") Long id){
//        examResultService.removeExamResult(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//}
