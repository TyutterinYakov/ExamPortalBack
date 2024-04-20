//package portal.api.controller.priv;
//
//import java.security.Principal;
//import java.util.List;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import portal.api.dto.QuestionDto;
//import portal.api.service.ExamResultService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//@RequestMapping("/api/exam/user-result")
//@RequiredArgsConstructor
//public class PrivateExamResultController {
//
//	private final ExamResultService examResultService;
//
//	@GetMapping("{quizeId}")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> checkUserResultByQuizeId(@PathVariable("quizeId") Long id, Principal principal) {
//		return ResponseEntity.ok(examResultService.checkUserResultExam(principal.getName(), id));
//	}
//
//	@PostMapping("submit")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> submitTestAnswer(@RequestBody List<QuestionDto> questions, Principal principal) {
//		return new ResponseEntity<>(examResultService.getExamResult(
//								principal.getName(), questions), HttpStatus.CREATED);
//	}
//
//	@GetMapping("all")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> getAllResultsExamsByUser(Principal principal){
//		return ResponseEntity.ok(examResultService.checkUserAllResultExam(principal.getName()));
//	}
//}
