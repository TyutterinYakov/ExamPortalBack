package portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import portal.model.Quize;
import portal.service.QuizeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quize")
public class QuizeController {
	
	@Autowired
	private QuizeService quizeService;
	
	
	@PostMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Quize> addQuize(@RequestBody Quize quize){
		return ResponseEntity.ok(quizeService.addQuize(quize));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public Quize getQuize(@PathVariable Long id) {
		return quizeService.getQuize(id);
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<Quize> updateQuize(@RequestBody Quize quize){
		return ResponseEntity.ok(quizeService.updateQuize(quize));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void removeQuize(@PathVariable Long id) {
		quizeService.removeQuize(id);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Quize>> getAllQuize() {
		return ResponseEntity.ok(quizeService.listQuize());
	}
	
	@GetMapping("/category/{categoryId}")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<List<Quize>> getQuiziesOfCategory(@PathVariable("categoryId") Long id){
		Category ct = new Category();
		ct.setCategoryId(id);
		return quizeService.getQuiziesOfCategory(ct);
	}
}
