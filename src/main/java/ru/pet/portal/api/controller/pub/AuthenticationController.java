package ru.pet.portal.api.controller.pub;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.mapper.UserMapper;
import ru.pet.portal.api.controller.dto.user.LoginResponseDto;
import ru.pet.portal.api.controller.dto.user.LoginUserDto;
import ru.pet.portal.api.controller.dto.user.RegisterUserDto;
import ru.pet.portal.api.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	private final UserMapper userMapper;
	private final SecurityContextLogoutHandler logoutHandler;

	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginUserDto loginUserDto) {
		return authenticationService.login(loginUserDto);
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody @Valid RegisterUserDto registerUserDto) {
		authenticationService.register(userMapper.toEntity(registerUserDto));
	}

	@PostMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response){
		logoutHandler.logout(request, response, null);
	}

}
