package portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import portal.dao.RoleRepository;
import portal.exception.UserNotFoundException;
import portal.model.User;
import portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleDao;
	
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
		
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String userName) {
		
		User user = userService.findUserByUserName(userName);
		
		return user;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
//	@ExceptionHandler(UserNotFoundException.class)
//	public ResponseEntity<?> exceptionHandler(UserNotFoundException ex){
//		return null;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/login")
//	public String login() {
//		User user = new User();
//		
//		user.setFirstName("Yasha");
//		user.setLastName("Tyutterin");
//		user.setEmail("tyutterin_yasha@mail.ru");
//		user.setPassword("12345");
//		user.setPhone("+79818837810");
//		user.setProfile("user");
//		user.setUserName("yaska1234");
//		Set<Role> roles = new HashSet<>();
//		roles.add(roleDao.getOne(1L));
//		roles.add(roleDao.getOne(2L));
//		
//		userService.createUser(user, roles);
//		
//		return "redirect:/login";
//	}
}
