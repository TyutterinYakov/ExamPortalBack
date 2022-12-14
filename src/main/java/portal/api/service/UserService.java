package portal.api.service;


import portal.api.dto.UserDto;
import portal.api.dto.UserDtoRequest;
import portal.store.entity.User;

public interface UserService {


	void register(UserDtoRequest user);
	void delete(String email);
	User findByEmail(String email);
	public void update(String name, UserDto user);
}
