package portal.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import portal.api.exception.UserNotFoundException;
import portal.api.security.JwtTokenProvider;
import portal.api.service.UserService;
import portal.store.entity.JwtRequest;
import portal.store.entity.User;

@Service
public class LoginServiceImpl {
	
	private final AuthenticationManager authManager;
	private UserService userService;
	private JwtTokenProvider jwtProvider;
	
	@Autowired
	public LoginServiceImpl(AuthenticationManager authManager, UserService userService, JwtTokenProvider jwtProvider) {
		super();
		this.authManager = authManager;
		this.userService = userService;
		this.jwtProvider = jwtProvider;
	}
	
	
	
	@Transactional
	public Map<Object, Object> getAuthenticate(JwtRequest request) {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
											request.getUserName(),
											request.getPassword()));
			User user = userService.findUserByUserName(request.getUserName());
			String token = jwtProvider.createToken(
					request.getUserName(),
					user.getRole()
						.name());
			Map<Object, Object> response = new HashMap<>();
			response.put("username", request.getUserName());
			response.put("token", token);
			
			return response;
	}
}
