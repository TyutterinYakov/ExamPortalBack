package portal.api.controller;


import java.util.List;

import javax.validation.Valid;

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

import portal.api.dto.CategoryDto;
import portal.api.exception.BadRequestException;
import portal.api.service.CategoryService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	//WRITE
	public static final String ADD_CATEGORY = "/api/categories";
	public static final String UPDATE_CATEGORY = "/api/categories";
	public static final String DELETE_CATEGORY = "/api/categories/{categoryId}";
	//READ
	public static final String GET_CATEGORY_BY_ID = "/api/categories/{categoryId}";
	public static final String GET_ALL_CATEGORY = "/api/categories";
	
	
	//WRITE
	
	@PostMapping(ADD_CATEGORY) 
	@PreAuthorize("hasAuthority('developers:write')") 			//Add category
	public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryDto categoryDto, BindingResult result) {
		checkValidateForm(result);
		return new ResponseEntity<>(categoryService.addCategory(categoryDto),
				HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_CATEGORY)
	@PreAuthorize("hasAuthority('developers:write')")  				//Update category
	public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDto categoryDto, BindingResult result)
	{
		checkValidateForm(result);
		return new ResponseEntity<>(categoryService.updateCategory(categoryDto),
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(DELETE_CATEGORY)
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> removeCategory(@PathVariable("categoryId") Long categoryId) { 	//delete category
		categoryService.removeCategoryById(categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//READ
	
	@GetMapping(GET_CATEGORY_BY_ID)
	@PreAuthorize("hasAuthority('developers:read')") 					//get category by id
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId) {
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId)); 
	}
	
	@GetMapping(GET_ALL_CATEGORY)
	@PreAuthorize("hasAuthority('developers:read')") 			//get all category
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(categoryService.getAllCategory());
	}
	
	
	
	
	//OTHER
	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new BadRequestException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}
	
	
	
	
}
