package portal.api.controller;


import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.api.exception.UserNotFoundException;
import portal.api.security.UserDetailsServiceImpl;
import portal.api.service.impl.LoginServiceImpl;
import portal.store.entity.JwtRequest;
import portal.store.entity.UserEntity;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {

	private UserDetailsServiceImpl userDetailsService;
	private LoginServiceImpl loginService;
	
	@Autowired
	public LoginController(UserDetailsServiceImpl userDetailsService, LoginServiceImpl loginService) {
		super();
		this.userDetailsService = userDetailsService;
		this.loginService=loginService;
	}
	
	public static final String GENERATE_TOKEN_BY_USERNAME_AND_PASSWORD = "/api/auth/generate-token";
	public static final String LOGOUT_USER = "/api/auth/logout";
	public static final String GET_CURRENT_USER_BY_PRINCIPAL = "/api/auth/current-user";
	

	@PostMapping(GENERATE_TOKEN_BY_USERNAME_AND_PASSWORD)
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) throws UserNotFoundException{
		return ResponseEntity.ok(loginService.getAuthenticate(request));
	}
	
	@PostMapping(LOGOUT_USER)
	public void logout(HttpServletRequest request, HttpServletResponse response){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, null);
	}
	
	@GetMapping(GET_CURRENT_USER_BY_PRINCIPAL)
	public UserEntity getCurrentUser(Principal principal) {
		return userDetailsService.getUser(principal.getName());
	}
}
