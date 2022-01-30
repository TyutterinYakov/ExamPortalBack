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
import portal.model.Category;
import portal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> addCategory(@RequestBody @Valid Category cat, BindingResult result) {
		try {
		if(result.hasErrors()) {
			throw new InvalidDataException();
		}
		Category category = categoryService.addCategory(cat);
		return ResponseEntity.ok(category);
		} catch(InvalidDataException ex) {
			logger.error(cat.toString(), ex);
			return new ResponseEntity<>("Ошибка при вводе данных категории", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(categoryService.getCategory(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok(categoryService.listCategory());
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> updateCategory(@RequestBody @Valid Category category, BindingResult result) 
	{
		try {
		if(result.hasErrors()) {
			throw new InvalidDataException();
		}
			return ResponseEntity.ok(categoryService.updateCategory(category));
		} catch(InvalidDataException ex) {
			logger.error(category.toString(), ex);
			return new ResponseEntity<>("Ошибка ввода данных для обновления категории", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void removeCategory(@PathVariable("id") Long id) {
		categoryService.removeCategory(id);
	}
	
	
}
