package ru.pet.portal.api.service;

import ru.pet.portal.store.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    void deleteById(UUID categoryId);
    void create(Category category);
    void update(UUID categoryId, Category category);
    Category getById(UUID categoryId);

    List<Category> getAll(int from, int size);
}
