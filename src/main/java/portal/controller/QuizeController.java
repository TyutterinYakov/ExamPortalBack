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

import portal.model.Quize;
import portal.service.QuizeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quize")
public class QuizeController {
	
	@Autowired
	private QuizeService quizeService;
	
	
	@PostMapping("/")
	public ResponseEntity<Quize> addQuize(@RequestBody Quize quize){
		return ResponseEntity.ok(quizeService.addQuize(quize));
	}
	
	@GetMapping("/{id}")
	public Quize getQuize(@PathVariable Long id) {
		return quizeService.getQuize(id);
	}
	
	@PutMapping("/")
	public ResponseEntity<Quize> updateQuize(@RequestBody Quize quize){
		return ResponseEntity.ok(quizeService.updateQuize(quize));
	}
	
	@DeleteMapping("/{id}")
	public void removeQuize(@PathVariable Long id) {
		quizeService.removeQuize(id);
	}
	
	@GetMapping("/")
	public List<Quize> getAllQuize() {
		return quizeService.listQuize();
	}
}
