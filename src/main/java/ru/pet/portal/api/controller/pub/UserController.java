package ru.pet.portal.api.controller.pub;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.UserMapper;
import ru.pet.portal.api.controller.dto.user.UserRequestDto;
import ru.pet.portal.api.service.UserService;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

//	@PostMapping("generate-token")
//	public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) {
//		return ResponseEntity.ok(loginService.getAuthenticate(request));
//	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody @Valid UserRequestDto userRequestDto) {
		userService.register(userMapper.toEntity(userRequestDto));
	}

//	@PostMapping("logout")
//	public void logout(HttpServletRequest request, HttpServletResponse response){
//		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//		logoutHandler.logout(request, response, null);
//	}

//	@GetMapping("current-user")
//	public User getCurrentUser(Principal principal) {
//		return userDetailsService.getUser(principal.getName());
//	}
}
