package portal.api.controller;

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

import portal.api.dto.QuizeDto;
import portal.api.exception.BadRequestException;
import portal.api.service.QuizeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping
public class QuizeController {
	
	private QuizeService quizeService;
	
	@Autowired
	public QuizeController(QuizeService quizeService) {
		super();
		this.quizeService = quizeService;
	}
	//WRITE
	private static final String GET_ANY_QUIZIES_BY_CATEGORY_ID="/categories/{categoryId}/quizies/any";
	private static final String ADD_QUIZE_FROM_CATEGORY="/categories/quizies";
	private static final String GET_ANY_QUIZE_BY_QUIZE_ID="/categories/quizies/{quizeId}/any";
	private static final String UPDATE_QUIZE_BY_QUIZE_ID="/categories/quizies";
	private static final String DELETE_QUIZE_BY_QUIZE_ID="/categories/quizies/{quizeId}";
	private static final String GET_ANY_QUIZIES="/categories/quizies/any";
	//READ
	private static final String GET_ACTIVE_QUIZE_BY_QUIZE_ID="/categories/quizies/{quizeId}";
	private static final String GET_ACTIVE_QUIZIES_BY_CATEGORY_ID="/categories/{categoryId}/quizies";
	private static final String GET_ACTIVE_QUIZIES="/categories/quizies";
	

	
	//WRITE
	
	@GetMapping(GET_ANY_QUIZIES_BY_CATEGORY_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<QuizeDto>> getAnyQuiziesByCategory(@PathVariable("categoryId") Long categoryId)
	{
		return ResponseEntity.ok(quizeService.getAnyQuiziesByCategoryId(categoryId));
	}
	
	@PostMapping(ADD_QUIZE_FROM_CATEGORY)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> addQuize(@RequestBody @Valid QuizeDto quizeDto, BindingResult result)
	{
		checkValidateForm(result);
		return new ResponseEntity<>(quizeService.addQuize(quizeDto), HttpStatus.CREATED);
	}
	
	@GetMapping(GET_ANY_QUIZE_BY_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<QuizeDto> getAnyQuize(@PathVariable("quizeId") Long quizeId) {
		return ResponseEntity.ok(quizeService.getQuizeAny(quizeId));
	}
	
	@PutMapping(UPDATE_QUIZE_BY_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<QuizeDto> updateQuize(@RequestBody @Valid QuizeDto quizeDto,
			BindingResult result) {
		checkValidateForm(result);
		return ResponseEntity.ok(quizeService.updateQuize(quizeDto));
	}
	
	@DeleteMapping(DELETE_QUIZE_BY_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> removeQuize(@PathVariable Long quizeId) {
		quizeService.deleteQuizeById(quizeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(GET_ANY_QUIZIES)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<QuizeDto>> getAnyQuizies() {
		return ResponseEntity.ok(quizeService.getAnyQuizies());
	}
	
	
	
	//READ
	
	@GetMapping(GET_ACTIVE_QUIZE_BY_QUIZE_ID)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<QuizeDto> getActiveQuize(@PathVariable("quizeId") Long quizeId) {
		return ResponseEntity.ok(quizeService.getActiveQuize(quizeId));
	}
	
	
	@GetMapping(GET_ACTIVE_QUIZIES)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<QuizeDto>> getActiveQuizies() {
		return ResponseEntity.ok(quizeService.getActiveQuizies());
	}
	
	@GetMapping(GET_ACTIVE_QUIZIES_BY_CATEGORY_ID)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<QuizeDto>> getActiveQuiziesByCategoryId(
			@PathVariable("categoryId") Long categoryId){
		return ResponseEntity.ok(quizeService.getActiveQuiziesByCategoryId(categoryId));
	}
	
	

	
	
	//OTHER
	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new BadRequestException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}

	
}
