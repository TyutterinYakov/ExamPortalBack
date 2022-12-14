package portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portal.api.dto.UserDto;
import portal.api.dto.UserDtoRequest;
import portal.api.exception.NotFoundException;
import portal.api.service.UserService;
import portal.store.entity.Role;
import portal.store.entity.User;
import portal.store.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void register(UserDtoRequest userDtoRequest) {
		userDao.save(buildNew(userDtoRequest));
	}

	@Override
	public User findByEmail(String email){
		return userDao.findByEmail(email).orElseThrow(() ->
				new NotFoundException("Пользователь с почтой " + email + " не найден"));
	}

	@Override
	@Transactional
	public void delete(String email) {
		userDao.findByEmail(email).ifPresent((u) -> userDao.deleteById(u.getId()));
	}

	@Override
	@Transactional
	public void update(String userName, UserDto userDto) {
		User user = findByEmail(userName);
		updateHelper(userDto, user);
	}

	
	private User buildNew(UserDtoRequest userDtoRequest) {
		return User.builder()
				.email(userDtoRequest.getEmail())
				.firstName(userDtoRequest.getFirstName())
				.lastName(userDtoRequest.getLastName())
				.phone(userDtoRequest.getPhone())
				.role(Role.USER)
				.password(passwordEncoder.encode(userDtoRequest.getPassword()))
				.build();

	}

	private void updateHelper(UserDto userDto, User user) {
		String firstName = userDto.getFirstName();
		if (firstName != null && !firstName.isBlank()) {
			user.setFirstName(firstName);
		}
		String lastName = userDto.getLastName();
		if (lastName != null && !lastName.isBlank()) {
			user.setLastName(lastName);
		}
		String email = userDto.getEmail();
		if (email != null && !email.isBlank()) {
			user.setEmail(email);
		}
		String phone = userDto.getPhone();
		if (phone != null && !phone.isBlank()) {
			user.setPhone(phone);
		}




	}




}
