package portal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import portal.config.JwtUtils;
import portal.exception.UserNotFoundException;
import portal.model.JwtRequest;
import portal.model.JwtResponse;
import portal.model.User;
import portal.service.impl.UserDetailServiceImpl;

@RestController
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserDetailServiceImpl detailServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authentificate(jwtRequest.getUserName(), jwtRequest.getPassword());
			
		} catch (UserNotFoundException ex) {
			ex.printStackTrace();
			throw new Exception("User not found");
		}
		
		
		UserDetails userDetails = detailServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		String token = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	
	
	private void authentificate(String userName, String password) throws Exception {
		try {
			
			manager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
		} catch(DisabledException e) {
			throw new Exception("User DISABLE");
		} catch(BadCredentialsException ex) {
			throw new Exception("Invalid Credentials "+ex.getMessage());
		}
				
	}
	
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User)detailServiceImpl.loadUserByUsername(principal.getName());
	}
}
