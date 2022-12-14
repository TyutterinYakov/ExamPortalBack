package portal.api.dto.mapper;

import org.springframework.stereotype.Component;

import portal.api.dto.UserDto;
import portal.store.entity.User;

@Component
public class UserMapper {

	public UserDto toDto(User entity) {
		return new UserDto(
					entity.getFirstName(),
					entity.getLastName(),
					entity.getEmail(),
					entity.getPhone()
				);
	}
}
