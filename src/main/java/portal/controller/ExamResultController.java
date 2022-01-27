package portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;
import portal.service.ExamResultService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/exam")
public class ExamResultController {
	
	
	private ExamResultService examResultService;
	
	@Autowired
	public ExamResultController(ExamResultService examResultService) {
		super();
		this.examResultService = examResultService;
	}

	@GetMapping("/statistic/{quizeId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<ExamResult>> getAllExamResultFromQuize(@PathVariable("quizeId") Long id){
		return examResultService.getAllResultFromQuize(id);
	}
	
	@GetMapping("/checkUserResult/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<ExamResult>> checkUserResult(@PathVariable("quizeId") Long id, Principal principal) throws UserNotFoundException{
		return examResultService.checkUserResultExam(principal.getName(), id);
	}
	
	@DeleteMapping("/examResult/{answerId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void deleteUserResult(@PathVariable("answerId") Long id){
		examResultService.removeExamResult(id);
	}
	
	@PostMapping("/eval-quize")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<ExamResult> evalQuize(@RequestBody List<Question> questions, Principal principal) throws UserNotFoundException, NotFoundException{
		
		return ResponseEntity.ok(examResultService.getExamResult(principal.getName(), questions));
	}
	
	@GetMapping("/checkAllUserResult")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<ExamResult>> checkAllUserResult(Principal principal) throws UserNotFoundException{
		return examResultService.checkUserAllResultExam(principal.getName());
	}
}
