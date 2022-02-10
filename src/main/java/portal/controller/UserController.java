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
		try {
			if(result.hasErrors()) {
				throw new InvalidDataException();
			}
				return ResponseEntity.ok(userService.createUser(user));
		} catch(UserFoundException ex) {
			logger.error(user.toString(), ex);
			return new ResponseEntity<>("Такой пользователь уже есть", HttpStatus.BAD_REQUEST);
		} catch (InvalidDataException e) {
			logger.error(user.toString(), e);
			return new ResponseEntity<>("Введены неверные данные", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@PutMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> updateUserProfile(Principal principal, @RequestBody @Valid User user, BindingResult result) {
		try {
			if(result.hasErrors()) {
				throw new InvalidDataException();
			}
			return ResponseEntity.ok(userService.updateUser(principal.getName(),user));
		} catch(NotPermissionException ex) {
			logger.error(user.toString(), ex);
			return new ResponseEntity<>("У вас нет прав изменять этого пользователя", HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
			logger.error(user.toString(), e);
			return new ResponseEntity<>("Такой пользователь не найден", HttpStatus.BAD_REQUEST);
		} catch(InvalidDataException exception) {
			logger.error(user.toString(), exception);
			return new ResponseEntity<>("Введены неверные данные", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@DeleteMapping("/")
	@PreAuthorize("hasAuthority('developers:read')")
	public void deleteUser(Principal principal) {
	
		userService.deleteUser(principal.getName());
	}
	
	@GetMapping("/image")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> getImageProfile(Principal principal){
		
		try {
			return ResponseEntity.ok(userService.getImageProfile(principal.getName()));
		} catch (UserNotFoundException e) {
			logger.error("Пользователь не найден", e);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			logger.error("Ошибка при чтении файла", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(NullPointerException ex) {
			logger.error("Необходима переавторизация", ex);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	@PostMapping("/image")
	@PreAuthorize("hasAuthority('developers:read')")
	public ResponseEntity<?> addImageProfile(@RequestParam("image") MultipartFile file, Principal principal){
		try {
			if(file.isEmpty()||principal.getName().isEmpty()) {
				throw new NullPointerException();
			}
			userService.addImageProfile(principal.getName(), file);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(NullPointerException ex) {
			logger.error("Файл не отправлен или principal пуст", ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(IOException e) {
			logger.error("Ошибка при записи файла", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			logger.error("Ошибка пользователя", e);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
}