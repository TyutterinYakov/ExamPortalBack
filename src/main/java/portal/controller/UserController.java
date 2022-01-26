package portal.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.exception.InvalidDataException;
import portal.exception.NotPermissionException;
import portal.exception.UserNotFoundException;
import portal.model.User;
import portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping("/")
	public User createUser(@RequestBody @Valid User user, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
		return userService.createUser(user);
		}
		throw new InvalidDataException("Неверные данные!");
		
	}
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> updateQuestion(Principal principal, @RequestBody @Valid User user, BindingResult result) throws NotPermissionException, UserNotFoundException, InvalidDataException{
		if(!result.hasErrors()) {
			return ResponseEntity.ok(userService.updateUser(principal.getName(),user));
		}
		throw new InvalidDataException("Неверные данные!");
	}
	
	
//	@GetMapping("/")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public User getUser(Principal principal) {
//		
//		User user = userService.findUserByUserName(principal.getName());
//		
//		return user;
//	}
	
	@DeleteMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public void deleteUser(Principal principal) {
		userService.deleteUser(principal.getName());
	}
	
}