package portal.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.exception.InvalidDataException;
import portal.exception.UserFoundException;
import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;
import portal.service.ExamResultService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/exam")
public class ExamResultController {
	
	private static Logger logger = LoggerFactory.getLogger(ExamResultController.class);
	
	private ExamResultService examResultService;
	
	@Autowired
	public ExamResultController(ExamResultService examResultService) {
		super();
		this.examResultService = examResultService;
	}

	@GetMapping("/statistic/{quizeId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<ExamResult>> getAllExamResultFromQuize(@PathVariable("quizeId") Long id){
		return ResponseEntity.ok(examResultService.getAllResultFromQuize(id));
	}
	
	@GetMapping("/checkUserResult/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> checkUserResult(@PathVariable("quizeId") Long id, Principal principal) {
		return ResponseEntity.ok(examResultService.checkUserResultExam(principal.getName(), id));
	}
	
	@DeleteMapping("/examResult/{answerId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> deleteUserResult(@PathVariable("answerId") Long id){
		examResultService.removeExamResult(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/eval-quize")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> evalQuize(@RequestBody List<Question> questions, Principal principal) {
		return new ResponseEntity<>(examResultService.getExamResult(
								principal.getName(), questions), HttpStatus.CREATED);
	}
	
	@GetMapping("/checkAllUserResult")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> checkAllUserResult(Principal principal){
		return ResponseEntity.ok(examResultService.checkUserAllResultExam(principal.getName()));
	}
}
