package portal.api.dto.factory;

import org.springframework.stereotype.Component;

import portal.api.dto.UserDto;
import portal.store.entity.UserEntity;

@Component
public class UserDtoFactory {

	
	public UserDto createUserDto(UserEntity entity) {
		return new UserDto(
					entity.getUserId(),
					entity.getUserName(),
					entity.getFirstName(),
					entity.getLastName(),
					entity.getEmail(),
					entity.getPhone()
				);
	}
}
