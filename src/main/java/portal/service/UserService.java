package portal.service;


import portal.exception.NotPermissionException;
import portal.exception.UserNotFoundException;
import portal.model.User;

public interface UserService {

//	public User createUser(User user) throws Exception;
	public User findUserByUserName(String userName);
	public void deleteUser(String userName);
	public User createUser(User user) throws Exception;
	public User updateUser(String name, User user) throws NotPermissionException, UserNotFoundException;
	
}
