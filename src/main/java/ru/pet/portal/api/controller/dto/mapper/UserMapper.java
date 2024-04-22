package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.user.RegisterUserDto;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;
import ru.pet.portal.store.entity.UserE;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserE toEntity(RegisterUserDto userRequestDto);
    UserResponseDto toDto(UserE userE);
}
