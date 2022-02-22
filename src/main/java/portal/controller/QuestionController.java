package portal.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import portal.model.Question;
import portal.service.QuestionService;

@RequestMapping("/question")
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	private QuestionService questionService;
	
	@Autowired
	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}


	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> getQuestion(@PathVariable Long id){
		return ResponseEntity.ok(questionService.getQuestion(id));
	}
	
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<Question>> getAllQuestion(){
		return ResponseEntity.ok(questionService.getListQuestion());
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question question, BindingResult result){
		checkValidateForm(result);
		return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> updateQuestion(@RequestBody @Valid Question question, BindingResult result){
		checkValidateForm(result);
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
		questionService.removeQuestion(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/quize/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<Set<Question>> getQuestionOfQuize(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}
	@GetMapping("/quize/all/{quizeId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Set<Question>> getQuestionOfQuizeAdmin(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getAllQuestionsOfQuize(quizeId));
	}

	
	
	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new InvalidDataException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}
	
	

}
