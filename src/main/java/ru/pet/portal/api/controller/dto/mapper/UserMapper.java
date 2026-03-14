package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;
import ru.pet.portal.api.controller.dto.user.RegisterUserDto;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;
import ru.pet.portal.store.entity.PositionE;
import ru.pet.portal.store.entity.UserE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserE toEntity(RegisterUserDto userRequestDto);

    @Mapping(target = "positions", source = "positions", qualifiedByName = "toPositionNames")
    UserResponseDto toDto(UserE userE);

    @Named("toPositionNames")
    static Set<String> toPositionNames(List<PositionE> positions) {
        if (CollectionUtils.isEmpty(positions)) {
            return null;
        }
        return positions.stream().map(PositionE::getName).collect(Collectors.toSet());
    }
}
