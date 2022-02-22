package portal.api.service;

import org.springframework.web.multipart.MultipartFile;

import portal.api.dto.UserDto;
import portal.store.entity.UserEntity;

public interface UserService {

	public UserEntity findUserByUserName(String userName);
	public void deleteUser(String userName);
	public UserDto createUser(UserEntity user);
	public UserDto updateUser(String name, UserDto user);
	public void addImageProfile(String name, MultipartFile file);
	public byte[] getImageProfile(String name);
	
}
