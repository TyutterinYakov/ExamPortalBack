package portal.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import portal.api.exception.NotFoundException;
import portal.api.exception.UserFoundException;
import portal.api.exception.UserNotFoundException;
import portal.api.service.UserService;
import portal.api.util.UploadAndRemoveImage;
import portal.store.entity.User;
import portal.store.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userDao;
	private UploadAndRemoveImage imageUtil;
	
	@Autowired
	public UserServiceImpl(UserRepository userDao, UploadAndRemoveImage imageUtil) {
		super();
		this.userDao = userDao;
		this.imageUtil = imageUtil;
	}
	
	
	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}


	@Override
	public User createUser(User user) throws UserFoundException {
		
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
		return userDao.saveAndFlush(user);
	}

	@Override
	public User findUserByUserName(String userName){
		return userDao.findByUserName(userName).orElseThrow(()->
					new NotFoundException("Пользователь не найден"));
	}

	@Override
	public void deleteUser(String userName) {
		userDao.deleteById(findUserByUserName(userName).getUserId());
	}

	@Override
	public User updateUser(String userName, User user) {
		User userOld = findUserByUserName(userName);
		userOld.setFirstName(user.getFirstName());
		userOld.setLastName(user.getLastName());
		userOld.setPhone(user.getPhone());
		userOld.setEmail(user.getEmail());
		return userDao.saveAndFlush(userOld);
	}

	@Override
	public void addImageProfile(String name, MultipartFile file) {
		User user = getUserByUsername(name);
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
	
	private User getUserByUsername(String userName) throws UserNotFoundException {
		return userDao.findByUserName(userName).orElseThrow(()->
			new UserNotFoundException());
	}


}
