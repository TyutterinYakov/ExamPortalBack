package portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import portal.dao.UserRepository;
import portal.exception.UserFoundException;
import portal.model.Role;
import portal.model.User;
import portal.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userDao;
	
	
	
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//	    return new BCryptPasswordEncoder();
//	}
//	
//	
//	
//	
//	@Override
//	public User createUser(User user) throws Exception {
//		
//		Optional<User> local = userDao.findByUserName(user.getUserName());
//		if(local.isPresent()) {
//			
//			throw new UserFoundException();
//		}
//		user.setPassword(this.passwordEncoder().encode(user.getPassword()));
//		user.setRole(Role.ADMIN);
//		user.setProfile("default.png");
//		userDao.save(user);
//		return user;
//	}

	@Override
	public User findUserByUserName(String userName){
		Optional<User> userOptional = userDao.findByUserName(userName);
		if(userOptional.isPresent()) {
		return userOptional.get();
		}
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

}
