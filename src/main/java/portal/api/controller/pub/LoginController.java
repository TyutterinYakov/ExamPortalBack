package portal.api.controller.pub;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
import portal.api.dto.JwtRequest;
import portal.store.entity.User;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class LoginController {

	private final UserDetailsServiceImpl userDetailsService;
	private final LoginServiceImpl loginService;
	

	@PostMapping("generate-token")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) throws UserNotFoundException{
		return ResponseEntity.ok(loginService.getAuthenticate(request));
	}
	
	@PostMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, null);
	}
	
	@GetMapping("current-user")
	public User getCurrentUser(Principal principal) {
		return userDetailsService.getUser(principal.getName());
	}
}
