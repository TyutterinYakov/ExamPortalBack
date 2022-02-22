package portal.api.service;

import org.springframework.web.multipart.MultipartFile;

import portal.store.entity.User;

public interface UserService {

	public User findUserByUserName(String userName);
	public void deleteUser(String userName);
	public User createUser(User user);
	public User updateUser(String name, User user);
	public void addImageProfile(String name, MultipartFile file);
	public byte[] getImageProfile(String name);
	
}
