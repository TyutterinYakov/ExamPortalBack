package ru.pet.portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.pet.portal.api.controller.dto.category.CategoryRequestDto;
import ru.pet.portal.api.controller.dto.validation.group.Create;
import ru.pet.portal.api.controller.dto.validation.group.Update;
import ru.pet.portal.api.controller.dto.mapper.CategoryMapper;
import ru.pet.portal.api.service.CategoryService;
import ru.pet.portal.store.entity.CategoryE;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin(origins = "${exam.portal.front-url}")
public class AdminCategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated(Create.class) CategoryRequestDto categoryDto) {
        final CategoryE categoryE = categoryMapper.toEntity(categoryDto);
        categoryService.create(categoryE);
    }

    @PutMapping("{categoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable UUID categoryId,
                       @RequestBody @Validated(Update.class) CategoryRequestDto categoryDto) {
        final CategoryE categoryE = categoryMapper.toEntity(categoryDto);
        categoryService.update(categoryId, categoryE);
    }

    @DeleteMapping("{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID categoryId) {
        categoryService.deleteById(categoryId);
    }
}
