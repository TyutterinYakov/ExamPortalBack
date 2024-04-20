//package portal.api.controller.priv;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import portal.api.dto.QuizeDto;
//import portal.api.exception.BadRequestException;
//import portal.api.service.QuizeService;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//@RequestMapping("/categories")
//@RequiredArgsConstructor
//public class PrivateQuizeController {
//
//	private final QuizeService quizeService;
//
//	@GetMapping("quizies/{quizeId}")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<QuizeDto> getActiveQuize(@PathVariable("quizeId") Long quizeId) {
//		return ResponseEntity.ok(quizeService.getActiveQuize(quizeId));
//	}
//
//	@GetMapping("quizies")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<List<QuizeDto>> getActiveQuizies() {
//		return ResponseEntity.ok(quizeService.getActiveQuizies());
//	}
//
//	@GetMapping("{categoryId}/quizies")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<List<QuizeDto>> getActiveQuiziesByCategoryId(
//			@PathVariable("categoryId") Long categoryId){
//		return ResponseEntity.ok(quizeService.getActiveQuiziesByCategoryId(categoryId));
//	}
//
//}
