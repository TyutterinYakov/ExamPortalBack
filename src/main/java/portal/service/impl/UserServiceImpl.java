package portal.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import portal.dao.UserRepository;
import portal.exception.NotPermissionException;
import portal.exception.UserFoundException;
import portal.exception.UserNotFoundException;
import portal.model.Role;
import portal.model.User;
import portal.service.UserService;
import portal.util.UploadAndRemoveImage;

@Service
public class UserServiceImpl implements UserService{

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
		
		Optional<User> local = userDao.findByUserName(user.getUserName());
		if(local.isPresent()) {
			
			throw new UserFoundException();
		}
		user.setPassword(this.passwordEncoder().encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setProfile("default.png");
		userDao.save(user);
		return user;
	}

	@Override
	public User findUserByUserName(String userName){
		Optional<User> userOptional = userDao.findByUserName(userName);
		if(userOptional.isPresent()) {
		return userOptional.get();
		}
		return null;
	}

	@Override
	public void deleteUser(String userName) {
		Optional<User> userOptional = userDao.findByUserName(userName);
		if(userOptional.isPresent()) {
			userDao.deleteById(userOptional.get().getUserId());
		}
	}

	@Override
	public User updateUser(String name, User user) throws NotPermissionException, UserNotFoundException {
		
		Optional<User> userOptional = userDao.findByUserName(name);
		if(userOptional.isPresent()) {
			if(!name.equals(user.getUserName())) {
				throw new NotPermissionException();
			}
			User us = userOptional.get();
			us=user;
			us.setFirstName(user.getFirstName());
			us.setLastName(user.getLastName());
			us.setPhone(user.getPhone());
			us.setEmail(user.getEmail());
			
			userDao.save(us);
			
			return us;
		}
		throw new UserNotFoundException();
	}

	@Override
	public void addImageProfile(String name, MultipartFile file) throws IOException, UserNotFoundException {
		User user = getUserByUsername(name);
		String imageName = imageUtil.uploadImage(file, "images/profile");
		imageUtil.deleteImage(user.getProfile(), "images/profile/");
		user.setProfile(imageName);
		userDao.save(user);
	}
	
	@Override
	public byte[] getImageProfile(String name) throws UserNotFoundException, IOException {
		User user = getUserByUsername(name);
		return imageUtil.getImage(user.getProfile(), "images/profile/");
	}
	
	
	private User getUserByUsername(String userName) throws UserNotFoundException {
		return userDao.findByUserName(userName).orElseThrow(()->
			new UserNotFoundException());
	}


}
