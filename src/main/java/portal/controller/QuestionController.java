package portal.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(questionService.addQuestion(question));
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(questionService.updateQuestion(question));
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
	public ResponseEntity<List<String>> evalQuize(@RequestBody List<Question> questions){
		
		List<String> answerCheck = new LinkedList<>();
		
		questions.forEach(q->{
			Question question = questionService.getQuestion(q.getQuestionId());
			if(q.getGivenAnswer().trim()==""||q.getGivenAnswer()==null) {
				answerCheck.add("skip");
			} 
			else if(question.getAnswer()!=null||question.getAnswer().trim().equals(q.getGivenAnswer().trim())) { 
				answerCheck.add("yes");
			} else {
				answerCheck.add("no");
			}
		});
		return ResponseEntity.ok(answerCheck);
	}

}
