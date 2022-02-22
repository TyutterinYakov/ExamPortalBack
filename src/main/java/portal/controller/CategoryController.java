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
	
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> addCategory(@RequestBody @Valid Category cat, BindingResult result) {
		if(result.hasErrors()) {
			throw new InvalidDataException("Поля не соответствуют всем условиям");
		}
		return new ResponseEntity<>(categoryService.addCategory(cat),
				HttpStatus.CREATED);
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
	public ResponseEntity<?> updateCategory(
			@RequestBody @Valid Category category, BindingResult result) 
	{
		if(result.hasErrors()) {
			throw new InvalidDataException("Ошибка ввода данных для обновления категории");
		}
		return new ResponseEntity<>(categoryService.updateCategory(category),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
		categoryService.removeCategory(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
