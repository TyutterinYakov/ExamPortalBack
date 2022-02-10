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
import portal.model.Quize;
import portal.service.QuizeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/quize")
public class QuizeController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuizeController.class);
	
	private QuizeService quizeService;
	
	@Autowired
	public QuizeController(QuizeService quizeService) {
		super();
		this.quizeService = quizeService;
	}

	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> addQuize(@RequestBody @Valid Quize quize, BindingResult result){
		
		try {
		if(result.hasErrors()) {
			throw new InvalidDataException("Ошибка при вводе данных теста");
		}
		return ResponseEntity.ok(quizeService.addQuize(quize));
		} catch (InvalidDataException ex) {
			logger.error(quize.toString(), ex);
			return new ResponseEntity<>("Ошибка при вводе данных теста", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getQuize(@PathVariable Long id) {
		return ResponseEntity.ok(quizeService.getQuize(id));
	}
	
	@GetMapping("/any/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> getQuizeAdmin(@PathVariable Long id) {
		return ResponseEntity.ok(quizeService.getQuizeAdmin(id));
	}
	
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> updateQuize(@RequestBody @Valid Quize quize, BindingResult result) {
		try {
		if(result.hasErrors()) {
			throw new InvalidDataException();
		}
		return ResponseEntity.ok(quizeService.updateQuize(quize));
		} catch(InvalidDataException ex) {
			logger.error(quize.toString(), ex);
			return new ResponseEntity<>("Ошибка при вводе данных теста", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void removeQuize(@PathVariable Long id) {
		
		quizeService.removeQuize(id);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Quize>> getAllQuizeUser() {
		return ResponseEntity.ok(quizeService.listQuize());
	}
	@GetMapping("/any")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<Quize>> getAllQuizeAdmin() {
		return ResponseEntity.ok(quizeService.listQuizeAny());
	}
	
	@GetMapping("/category/{categoryId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Quize>> getQuiziesOfCategory(@PathVariable("categoryId") Long id){
		return ResponseEntity.ok(quizeService.getQuiziesOfCategory(id));
	}
	
	@GetMapping("/category/any/{categoryId}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<List<Quize>> getQuiziesOfCategoryAll(@PathVariable("categoryId") Long id){
		return ResponseEntity.ok(quizeService.getQuiziesOfCategoryAll(id));
	}
	

	
}
