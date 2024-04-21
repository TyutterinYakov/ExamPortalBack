package ru.pet.portal.api.service;


import ru.pet.portal.api.controller.dto.user.LoginResponseDto;
import ru.pet.portal.api.controller.dto.user.LoginUserDto;
import ru.pet.portal.store.entity.User;

public interface AuthenticationService {
	void register(User user);
	LoginResponseDto login(LoginUserDto loginUserDto);
	User getUser(String name);
}
