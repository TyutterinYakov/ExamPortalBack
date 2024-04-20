package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.portal.api.service.UserService;
import ru.pet.portal.store.entity.User;
import ru.pet.portal.store.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new DataIntegrityViolationException(
                    "Пользователь с почтой " + u.getEmail() + " уже зарегистрирован!!!");
        });
        userRepository.save(user);
    }
}
