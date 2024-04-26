package ru.pet.portal.api.service;


import ru.pet.portal.api.controller.dto.user.LoginResponseDto;
import ru.pet.portal.api.controller.dto.user.LoginUserDto;
import ru.pet.portal.store.entity.UserE;

public interface AuthenticationService {
	void register(UserE userE);
	LoginResponseDto login(LoginUserDto loginUserDto);
}
