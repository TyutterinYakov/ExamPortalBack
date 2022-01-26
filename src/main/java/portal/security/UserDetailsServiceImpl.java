package portal.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portal.dao.UserRepository;
import portal.model.User;


@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private final UserRepository userDao;
	
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userDao) {
		super();
		this.userDao = userDao;
	}

	public User getUser(String name) {
		loadUserByUsername(name);
		User user = userDao.findByUserName(name).orElseThrow(()->
		new UsernameNotFoundException(name));
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username).orElseThrow(()->
		new UsernameNotFoundException(username));
		return SecurityUser.fromUser(user);
	}


}
