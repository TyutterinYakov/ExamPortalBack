package portal.controller;

import java.util.List;

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
import portal.exception.NotPermissionException;
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
	public Question getQuestion(@PathVariable Long id){
		
		return questionService.getQuestion(id);
	}
	
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public List<Question> getAllQuestion(){
		return questionService.getListQuestion();
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> addQuestion(@RequestBody @Valid Question question, BindingResult result){
		try {
			if(result.hasErrors()) {
				throw new InvalidDataException("Ошибка при вводе данных вопроса");
			}
			return ResponseEntity.ok(questionService.addQuestion(question));
		} catch(InvalidDataException ex) {
				logger.error(question.toString(), ex);
				return new ResponseEntity<>("Неверные данные для добавления вопроса", HttpStatus.BAD_REQUEST);
			}
		
		
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> updateQuestion(@RequestBody @Valid Question question, BindingResult result){
		try {
		if(result.hasErrors()) {
				throw new InvalidDataException("Ошибка при вводе данных вопроса");
			}
			return ResponseEntity.ok(questionService.updateQuestion(question));
		} catch(InvalidDataException ex) {
			logger.error(question.toString(), ex);
			return new ResponseEntity<>("Неверные данные для обновления вопроса", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void deleteQuestion(@PathVariable Long id) {
		questionService.removeQuestion(id);
	}
	
	@GetMapping("/quize/{quizeId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getQuestionOfQuize(@PathVariable("quizeId") Long quizeId){
		try {
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
		} catch(NotPermissionException ex) {
			logger.error(quizeId.toString(), ex);
			return new ResponseEntity<>("Доступ к тесту и этим вопросам закрыт" , HttpStatus.FORBIDDEN);
		} catch( InvalidDataException e) {
			logger.error(quizeId.toString(), e);
			return new ResponseEntity<>("теста с таким id нет" , HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/quize/all/{quizeId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> getQuestionOfQuizeAdmin(@PathVariable("quizeId") Long quizeId){
		try {
		return ResponseEntity.ok(questionService.getAllQuestionsOfQuize(quizeId));
		} catch(InvalidDataException ex) {
			logger.error(quizeId.toString(), ex);
			return new ResponseEntity<>("теста с таким id нет" , HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
