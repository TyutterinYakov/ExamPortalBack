//package portal.api.controller.priv;
//
//import java.util.List;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import portal.api.dto.QuestionDto;
//import portal.api.service.QuestionService;
//
//@RequestMapping("/api/quizies")
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//@RequiredArgsConstructor
//public class PrivateQuestionController {
//
//	private final QuestionService questionService;
//
//	@GetMapping("{quizeId}/questions")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<List<QuestionDto>> getByQuizeId(@PathVariable("quizeId") Long quizeId){
//		return ResponseEntity.ok(questionService.getQuestionsOfQuize(quizeId));
//	}
//
//
//}
