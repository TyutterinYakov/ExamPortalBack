package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.user.UserRequestDto;
import ru.pet.portal.store.entity.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);
}
