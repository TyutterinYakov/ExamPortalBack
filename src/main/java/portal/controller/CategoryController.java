package portal.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin("*")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category cat, BindingResult result) throws InvalidDataException {
		
		if(!result.hasErrors()) {
			Category category = categoryService.addCategory(cat);
			return ResponseEntity.ok(category);
		}
		
		throw new InvalidDataException("Ошибка при вводе данных категории");
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public Category getCategory(@PathVariable("id") Long id) {
		
		return categoryService.getCategory(id);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok(categoryService.listCategory());
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public Category updateCategory(@RequestBody @Valid Category category, BindingResult result) throws InvalidDataException {
		if(!result.hasErrors()) {
			return categoryService.updateCategory(category);
		}
		throw new InvalidDataException("Ошибка при вводе данных категории");
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void removeCategory(@PathVariable("id") Long id) {
		categoryService.removeCategory(id);
	}
	
	
}
