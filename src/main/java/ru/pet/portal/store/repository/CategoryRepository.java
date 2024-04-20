package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.store.entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    default Category findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Категория с идентификатором " + id + " отсутствует"));
    }
}
