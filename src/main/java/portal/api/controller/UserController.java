package portal.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import portal.api.dto.UserDto;
import portal.api.exception.BadRequestException;
import portal.api.service.UserService;
import portal.store.entity.UserEntity;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	private static final String DELETE_USER_BY_PRINCIPAL="/api/user";
	private static final String UPDATE_USER_PROFILE_BY_PRINCIPAL="/api/user";
	private static final String CREATE_USER="/api/user/";
	private static final String UPDATE_IMAGE_PROFILE_BY_PRINCIPAL="/api/user/image";
	private static final String GET_IMAGE_PROFILE_BY_PRINCIPAL="/api/user/image";
	
	
	@PostMapping(CREATE_USER)
	public ResponseEntity<?> createUser(@RequestBody @Valid UserEntity user, BindingResult result) {
		checkValidateForm(result);
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_USER_PROFILE_BY_PRINCIPAL)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> updateUserProfile(Principal principal, @RequestBody @Valid UserDto user, BindingResult result) {
		checkValidateForm(result);
		return ResponseEntity.ok(userService.updateUser(principal.getName(),user));
	}
	
	@DeleteMapping(DELETE_USER_BY_PRINCIPAL)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> deleteUser(Principal principal) {
		userService.deleteUser(principal.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(GET_IMAGE_PROFILE_BY_PRINCIPAL)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getImageProfile(Principal principal){
		return ResponseEntity.ok(userService.getImageProfile(principal.getName()));
	}
	@PostMapping(UPDATE_IMAGE_PROFILE_BY_PRINCIPAL)
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> addImageProfile(@RequestParam("image") MultipartFile file, Principal principal){
		userService.addImageProfile(principal.getName(), file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	private boolean checkValidateForm(BindingResult result) {
		if(result.hasErrors()) {
			throw new BadRequestException("Ошибка при вводе данных");
		}
		return result.hasErrors();
	}
	
	
	
}