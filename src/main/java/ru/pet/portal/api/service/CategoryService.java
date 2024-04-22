package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.CategoryE;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    void deleteById(UUID categoryId);
    void create(CategoryE categoryE);
    void update(UUID categoryId, CategoryE categoryE);
    CategoryE getById(UUID categoryId);

    List<CategoryE> getAll(int from, int size);
}
