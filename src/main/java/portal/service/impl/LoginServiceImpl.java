package portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import portal.exception.UserNotFoundException;
import portal.model.JwtRequest;
import portal.model.User;
import portal.security.JwtTokenProvider;
import portal.security.UserDetailsServiceImpl;
import portal.service.UserService;

@Service
public class LoginServiceImpl {
	
	private final AuthenticationManager authManager;
	private UserService userService;
	private JwtTokenProvider jwtProvider;
	
	public LoginServiceImpl(AuthenticationManager authManager, UserService userService, JwtTokenProvider jwtProvider) {
		super();
		this.authManager = authManager;
		this.userService = userService;
		this.jwtProvider = jwtProvider;
	}
	
	
	
	@Transactional
	public ResponseEntity<?> getAuthenticate(JwtRequest request) throws UserNotFoundException{
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
}
