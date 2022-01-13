package portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portal.dao.UserRepository;
import portal.model.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userDao; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userDao.findByUserName(username);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			return user;
		}
		throw new UsernameNotFoundException("No user found "+username);
	}

}
