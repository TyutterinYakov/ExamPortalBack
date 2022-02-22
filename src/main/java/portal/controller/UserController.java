package portal.controller;

import java.io.IOException;
import java.security.Principal;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import portal.exception.InvalidDataException;
import portal.exception.NotPermissionException;
import portal.exception.UserFoundException;
import portal.exception.UserNotFoundException;
import portal.model.User;
import portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult result) {
		checkValidateForm(result);
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> updateUserProfile(Principal principal, @RequestBody @Valid User user, BindingResult result) {
		checkValidateForm(result);
		return ResponseEntity.ok(userService.updateUser(principal.getName(),user));
	}
	
	@DeleteMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> deleteUser(Principal principal) {
		userService.deleteUser(principal.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/image")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getImageProfile(Principal principal){
		return ResponseEntity.ok(userService.getImageProfile(principal.getName()));
	}
	@PostMapping("/image")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> addImageProfile(@RequestParam("image") MultipartFile file, Principal principal){
		userService.addImageProfile(principal.getName(), file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new InvalidDataException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}
	
	
	
}