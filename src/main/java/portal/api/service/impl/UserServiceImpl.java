package portal.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import portal.api.dto.UserDto;
import portal.api.dto.factory.UserDtoFactory;
import portal.api.exception.NotFoundException;
import portal.api.exception.UserFoundException;
import portal.api.exception.UserNotFoundException;
import portal.api.service.UserService;
import portal.api.util.UploadAndRemoveImage;
import portal.store.entity.Role;
import portal.store.entity.UserEntity;
import portal.store.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userDao;
	private UserDtoFactory userDtoFactory;
	private UploadAndRemoveImage imageUtil;
	
	@Autowired
	public UserServiceImpl(UserRepository userDao, UserDtoFactory userDtoFactory, UploadAndRemoveImage imageUtil) {
		super();
		this.userDao = userDao;
		this.userDtoFactory = userDtoFactory;
		this.imageUtil = imageUtil;
	}


	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}


	@Override
	public UserDto createUser(UserEntity user) throws UserFoundException {
		
		userDao.findByUserName(
				user.getUserName())
					.ifPresent((u)->{
						throw new UserFoundException(
								String.format(
										"Пользователь с ником \"%s\" уже зарегистрирован",
										u.getUserName()));
						}
					);
		user.setPassword(this.passwordEncoder().encode(user.getPassword()));
		user.setRole(Role.USER);
		return userDtoFactory.createUserDto(userDao.saveAndFlush(user));
	}

	@Override
	public UserEntity findUserByUserName(String userName){
		return userDao.findByUserName(userName).orElseThrow(()->
					new NotFoundException("Пользователь не найден"));
	}

	@Override
	public void deleteUser(String userName) {
		userDao.deleteById(findUserByUserName(userName).getUserId());
	}

	@Override
	public UserDto updateUser(String userName, UserDto user) {
		return userDtoFactory
				.createUserDto(
						userDao.saveAndFlush(
								findUserByUserName(userName)
								.makeUserDto(user))
						);
	}

	@Override
	public void addImageProfile(String name, MultipartFile file) {
		UserEntity user = getUserByUsername(name);
		String imageName = imageUtil.uploadImage(file, "images/profile");
		imageUtil.deleteImage(user.getProfile(), "images/profile/");
		user.setProfile(imageName);
		userDao.save(user);
	}
	
	@Override
	public byte[] getImageProfile(String name) {
		return imageUtil.getImage(
					getUserByUsername(name)
					.getProfile(),
					"images/profile/"
				);
	}
	
	private UserEntity getUserByUsername(String userName) throws UserNotFoundException {
		return userDao.findByUserName(userName).orElseThrow(()->
			new UserNotFoundException());
	}


}
