package portal.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import portal.dao.RoleRepository;
import portal.dao.UserRepository;
import portal.exception.UserFoundException;
import portal.model.Role;
import portal.model.User;
import portal.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userDao;
	@Autowired
	private RoleRepository roleDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncdoder;
	
	
	
	
	@Override
	public User createUser(User user) throws Exception {
		
		Optional<User> local = userDao.findByUserName(user.getUserName());
		if(local.isPresent()) {
			
			throw new UserFoundException();
		}
		Set<Role> roles = new HashSet<>();
//		roles.add(roleDao.getOne(1L));
		roles.add(roleDao.getOne(2L));
		user.setPassword(this.passwordEncdoder.encode(user.getPassword()));
		user.setRoles(roles);
		user.setProfile("default.png");
		userDao.save(user);
		return user;
	}

	@Override
	public User findUserByUserName(String userName) {
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
