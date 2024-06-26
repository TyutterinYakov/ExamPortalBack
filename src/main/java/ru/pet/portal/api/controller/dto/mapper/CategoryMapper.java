package ru.pet.portal.api.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.pet.portal.api.controller.dto.category.CategoryRequestDto;
import ru.pet.portal.api.controller.dto.category.CategoryResponseDto;
import ru.pet.portal.store.entity.CategoryE;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {
    CategoryResponseDto toDto(CategoryE categoryE);
    CategoryE toEntity(CategoryRequestDto categoryDto);
}
