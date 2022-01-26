package portal.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.exception.UserNotFoundException;
import portal.model.JwtRequest;
import portal.model.User;
import portal.security.JwtTokenProvider;
import portal.security.UserDetailsServiceImpl;
import portal.service.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {
	
	private final AuthenticationManager authManager;
	private UserService userService;
	private JwtTokenProvider jwtProvider;
	private UserDetailsServiceImpl userDetailsService;
	
	
	public LoginController(AuthenticationManager authManager, UserService userService, JwtTokenProvider jwtProvider, UserDetailsServiceImpl userDetailsService) {
		super();
		this.authManager = authManager;
		this.userService = userService;
		this.jwtProvider = jwtProvider;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/generate-token")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) throws UserNotFoundException{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			User user = userService.findUserByUserName(request.getUserName());
			if(user==null) {
				throw new UserNotFoundException("Usent doesnt exists");
			}
			String token = jwtProvider.createToken(request.getUserName(), user.getRole().name());
			Map<Object, Object> response = new HashMap<>();
			response.put("username", request.getUserName());
			response.put("token", token);
			
			return ResponseEntity.ok(response);
		} catch(AuthenticationException ex) {
			return new ResponseEntity<>("Invalid email/password or all", HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, null);
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return userDetailsService.getUser(principal.getName());
	}
}
