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

import portal.model.Category;
import portal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category cat) {
		
		Category category = categoryService.addCategory(cat);
		
		return ResponseEntity.ok(category);
	}
	
	
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable("id") Long id) {
		
		return categoryService.getCategory(id);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok(categoryService.listCategory());
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/{id}")
	public void removeCategory(@PathVariable("id") Long id) {
		categoryService.removeCategory(id);
	}
	
	
}
