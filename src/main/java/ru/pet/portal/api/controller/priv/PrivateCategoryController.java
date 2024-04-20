package ru.pet.portal.api.controller.priv;


import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.category.CategoryResponseDto;
import ru.pet.portal.api.controller.dto.mapper.CategoryMapper;
import ru.pet.portal.api.service.CategoryService;
import ru.pet.portal.store.entity.Category;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class PrivateCategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("{categoryId}")
    public CategoryResponseDto getById(@PathVariable("categoryId") UUID categoryId) {
        final Category category = categoryService.getById(categoryId);
        return categoryMapper.toDto(category);
    }

    @GetMapping
    public List<CategoryResponseDto> getAll(@Min(0) @RequestParam(defaultValue = "0") int from,
                                            @Min(1) @RequestParam(defaultValue = "10") int size) {
        final List<Category> categories = categoryService.getAll(from, size);
        return categories.stream().map(categoryMapper::toDto).toList();
    }

}
