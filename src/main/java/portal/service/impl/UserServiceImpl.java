package portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import portal.dao.UserRepository;
import portal.exception.NotPermissionException;
import portal.exception.UserFoundException;
import portal.exception.UserNotFoundException;
import portal.model.Role;
import portal.model.User;
import portal.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userDao;
	
	
	
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

}
