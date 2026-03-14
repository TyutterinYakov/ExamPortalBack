package ru.pet.portal.api.controller.dto.position;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
public class PositionResponseDto {

    private UUID id;
    private String name;
}
