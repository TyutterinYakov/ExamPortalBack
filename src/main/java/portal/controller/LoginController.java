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
import portal.service.impl.LoginServiceImpl;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {
	

	private UserDetailsServiceImpl userDetailsService;
	private LoginServiceImpl loginService;
	
	
	public LoginController(UserDetailsServiceImpl userDetailsService, LoginServiceImpl loginService) {
		super();
		this.userDetailsService = userDetailsService;
		this.loginService=loginService;
	}

	@PostMapping("/generate-token")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) throws UserNotFoundException{
		return loginService.getAuthenticate(request);
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
