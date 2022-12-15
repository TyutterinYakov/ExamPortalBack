package portal.api.controller.priv;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.api.dto.CategoryDto;
import portal.api.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class PrivateCategoryController {

	private final CategoryService categoryService;
	
	@GetMapping("{categoryId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<CategoryDto> getById(@PathVariable("categoryId") Long categoryId) {
		return ResponseEntity.ok(categoryService.getById(categoryId));
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<CategoryDto>> getAll(){
		return ResponseEntity.ok(categoryService.getAll());
	}

}
