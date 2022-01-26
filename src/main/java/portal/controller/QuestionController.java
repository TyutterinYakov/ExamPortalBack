package portal.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.exception.InvalidDataException;
import portal.exception.UserNotFoundException;
import portal.model.ExamResult;
import portal.model.Question;
import portal.service.QuestionService;

@RequestMapping("/question")
@RestController
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public Question getQuestion(@PathVariable Long id){
		
		return questionService.getQuestion(id);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public List<Question> getAllQuestion(){
		return questionService.getListQuestion();
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question question, BindingResult result) throws InvalidDataException{
		if(!result.hasErrors()) {
			return ResponseEntity.ok(questionService.addQuestion(question));
		}
		throw new InvalidDataException("Ошибка при вводе данных вопроса");
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> updateQuestion(@RequestBody @Valid Question question, BindingResult result) throws InvalidDataException{
		if(!result.hasErrors()) {
			return ResponseEntity.ok(questionService.updateQuestion(question));
		}
		throw new InvalidDataException("Ошибка при вводе данных вопроса");
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void deleteQuestion(@PathVariable Long id) {
		questionService.removeQuestion(id);
	}
	
	@GetMapping("/quize/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Question>> getQuestionOfQuize(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}
	@GetMapping("/quize/all/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Question>> getQuestionOfQuizeAdmin(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}
	
	
	@PostMapping("/eval-quize")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<ExamResult> evalQuize(@RequestBody List<Question> questions, Principal principal) throws UserNotFoundException, NotFoundException{
		
		return ResponseEntity.ok(questionService.getExamResult(principal.getName(), questions));
	}

}
