package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.user.RegisterUserDto;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;
import ru.pet.portal.store.entity.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    User toEntity(RegisterUserDto userRequestDto);
    UserResponseDto toDto(User user);
}
