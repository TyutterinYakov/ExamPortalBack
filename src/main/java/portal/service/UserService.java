package portal.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import portal.model.Role;
import portal.model.User;

public interface UserService {

	public User createUser(User user) throws Exception;
	public User findUserByUserName(String userName);
	public void deleteUser(Long id);
	
}
