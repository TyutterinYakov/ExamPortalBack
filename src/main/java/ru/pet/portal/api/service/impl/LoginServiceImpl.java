//package portal.api.service.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.transaction.Transactional;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import portal.api.security.JwtTokenProvider;
//import portal.api.service.UserService;
//import portal.api.controller.dto.JwtRequest;
//import portal.store.entity.User;
//
//@Service
//@RequiredArgsConstructor
//public class LoginServiceImpl {
//
//	private final AuthenticationManager authManager;
//	private final UserService userService;
//	private final JwtTokenProvider jwtProvider;
//
//
//	@Transactional
//	public Map<Object, Object> getAuthenticate(JwtRequest request) {
//			authManager.authenticate(
//					new UsernamePasswordAuthenticationToken(
//											request.getUserName(),
//											request.getPassword()));
//			User user = userService.findByEmail(request.getUserName());
//			String token = jwtProvider.createToken(
//					request.getUserName(),
//					user.getRole()
//						.name());
//			Map<Object, Object> response = new HashMap<>();
//			response.put("username", request.getUserName());
//			response.put("token", token);
//
//			return response;
//	}
//}
