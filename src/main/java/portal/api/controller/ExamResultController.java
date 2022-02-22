package portal.api.controller;

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

import portal.api.exception.UserFoundException;
import portal.api.exception.UserNotFoundException;
import portal.api.service.ExamResultService;
import portal.store.entity.ExamResult;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping
public class ExamResultController {
	
	
	private ExamResultService examResultService;
	
	@Autowired
	public ExamResultController(ExamResultService examResultService) {
		super();
		this.examResultService = examResultService;
	}

	//WRITE
	private static final String GET_EXAM_RESULT_BY_QUIZE_ID = "/api/exam/result/{quizeId}";
	private static final String DELETE_EXAM_RESULT_BY_ANSWER_ID = "/api/exam/result/{answerId}";
	//READ
	private static final String GET_EXAM_RESULT_USER_BY_QUIZE_ID = "/api/exam/user-result/{quizeId}";
	private static final String SUBMIT_TEST_ANSWER = "/api/exam/user-result/submit";
	private static final String GET_ALL_EXAM_RESULT_BY_PRINCIPAL_USER = "/api/exam/user-result/all";
			
	
	
	//WRITE
	
	@GetMapping(GET_EXAM_RESULT_BY_QUIZE_ID) //get exam result by quize id
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<ExamResult>> getAllExamResultFromQuize(@PathVariable("quizeId") Long id){
		return ResponseEntity.ok(examResultService.getAllResultFromQuize(id));
	}
	
	@DeleteMapping(DELETE_EXAM_RESULT_BY_ANSWER_ID) //Delete exam result by answer id
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> deleteUserResultByAnswerId(@PathVariable("answerId") Long id){
		examResultService.removeExamResult(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	//READ
	
	@GetMapping(GET_EXAM_RESULT_USER_BY_QUIZE_ID) 
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> checkUserResultByQuizeId(@PathVariable("quizeId") Long id, Principal principal) {
		return ResponseEntity.ok(examResultService.checkUserResultExam(principal.getName(), id));
	}
	
	
//	@PostMapping(SUBMIT_TEST_ANSWER) 						///submit test answers
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> submitTestAnswer(@RequestBody List<Question> questions, Principal principal) {
//		return new ResponseEntity<>(examResultService.getExamResult(
//								principal.getName(), questions), HttpStatus.CREATED);
//	}
//	
	@GetMapping(GET_ALL_EXAM_RESULT_BY_PRINCIPAL_USER)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getAllResultsExamsByUser(Principal principal){
		return ResponseEntity.ok(examResultService.checkUserAllResultExam(principal.getName()));
	}
}
