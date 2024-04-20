package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.portal.api.controller.dto.user.LoginResponseDto;
import ru.pet.portal.api.controller.dto.user.LoginUserDto;
import ru.pet.portal.api.security.JwtService;
import ru.pet.portal.api.service.AuthenticationService;
import ru.pet.portal.store.entity.User;
import ru.pet.portal.store.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void register(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresentOrElse(u -> {
            throw new DataIntegrityViolationException(
                    "Пользователь с почтой " + u.getEmail() + " уже зарегистрирован!!!");
        }, () -> user.setPassword(passwordEncoder.encode(user.getPassword())));

        userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginUserDto loginUserDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserDto.getEmail());
        if (!passwordEncoder.matches(loginUserDto.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Некорректный пароль");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        String jwtToken = jwtService.generateToken(userDetails);

        return new LoginResponseDto().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
    }

    @Override
    public User getUser(String name) {
        return userRepository.findByEmail(name).orElseThrow();
    }
}
