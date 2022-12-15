package portal.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import portal.api.dto.QuestionDto;
import portal.api.exception.BadRequestException;
import portal.api.service.QuestionService;
import portal.store.entity.QuestionEntity;

@RequestMapping
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class QuestionController {
	
	private QuestionService questionService;
	
	@Autowired
	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}

	//WRITE
	public static final String GET_ANY_QUESTION = "/api/quizies/questions/{questionId}/any";
	public static final String GET_ANY_QUESTIONS = "/api/quizies/questions/any";
	public static final String ADD_QUESTION = "/api/quizies/questions";
	public static final String UPDATE_QUESTION = "/api/quizies/questions";
	public static final String DELETE_QUESTION_BY_ID = "/api/quizies/questions/{questionId}";
	public static final String GET_ANY_QUESTIONS_BY_QUIZE_ID = "/api/quizies/{quizeId}/questions/any";
	
	//READ
	public static final String GET_QUESTIONS_BY_ACTIVE_QUIZE_ID = "/api/quizies/{quizeId}/questions";
	
	
	
	//WRITE

	@GetMapping(GET_ANY_QUESTION)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<QuestionEntity> getQuestion(@PathVariable("questionId") Long questionId){
		return ResponseEntity.ok(questionService.getQuestionAnyById(questionId));
	}
	
	
	@GetMapping(GET_ANY_QUESTIONS)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<QuestionEntity>> getAllQuestion(){
		return ResponseEntity.ok(questionService.getAllAnyQuestion());
	}
	
	@PostMapping(ADD_QUESTION)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<QuestionEntity> addQuestion(@RequestBody @Valid QuestionEntity question, BindingResult result){
		checkValidateForm(result);
		return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_QUESTION)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<QuestionEntity> updateQuestion(@RequestBody @Valid QuestionEntity question, BindingResult result){
		checkValidateForm(result);
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	
	@DeleteMapping(DELETE_QUESTION_BY_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) {
		questionService.deleteQuestionById(questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(GET_ANY_QUESTIONS_BY_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<QuestionEntity>> getQuestionOfQuizeAdmin(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getAllQuestionsOfAnyQuize(quizeId));
	}
	
	
	//READ
	
	@GetMapping(GET_QUESTIONS_BY_ACTIVE_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<QuestionDto>> getQuestionOfQuize(@PathVariable("quizeId") Long quizeId){
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}
	
	
	
	//HELPER

	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new BadRequestException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}
	
	

}
