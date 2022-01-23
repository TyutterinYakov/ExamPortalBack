package portal.service;


import portal.model.User;

public interface UserService {

//	public User createUser(User user) throws Exception;
	public User findUserByUserName(String userName);
	public void deleteUser(Long id);
	
}
