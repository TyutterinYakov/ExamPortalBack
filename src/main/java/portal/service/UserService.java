package portal.service;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import portal.exception.NotPermissionException;
import portal.exception.UserFoundException;
import portal.exception.UserNotFoundException;
import portal.model.User;

public interface UserService {

	public User findUserByUserName(String userName);
	public void deleteUser(String userName);
	public User createUser(User user) throws UserFoundException;
	public User updateUser(String name, User user) throws NotPermissionException, UserNotFoundException;
	public void addImageProfile(String name, MultipartFile file) throws IOException, UserNotFoundException;
	public byte[] getImageProfile(String name) throws UserNotFoundException, IOException;
	
}
