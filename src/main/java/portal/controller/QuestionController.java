package portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.model.Question;
import portal.service.QuestionService;

@RequestMapping("/question")
@RestController
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	
	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable Long id){
		
		return questionService.getQuestion(id);
	}
	
	@GetMapping("/")
	public List<Question> getAllQuestion(){
		return questionService.getListQuestion();
	}
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(questionService.addQuestion(question));
	}
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	
	@DeleteMapping("/{id}")
	public void deleteQuestion(@PathVariable Long id) {
		questionService.removeQuestion(id);
	}
	
	@GetMapping("/quize/{quizeId}")
	public ResponseEntity<List<Question>> getQuestionOfQuize(@PathVariable("quizeId") Long quizeId){
		quizeId=(Long)quizeId;
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}
	@GetMapping("/quize/all/{quizeId}")
	public ResponseEntity<List<Question>> getQuestionOfQuizeAdmin(@PathVariable("quizeId") Long quizeId){
		quizeId=(Long)quizeId;
		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
	}

}
