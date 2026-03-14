package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.position.PositionResponseDto;
import ru.pet.portal.store.entity.PositionE;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PositionMapper {
    PositionResponseDto toDto(PositionE position);
    List<PositionResponseDto> toRsDtoList(List<PositionE> positions);
}
