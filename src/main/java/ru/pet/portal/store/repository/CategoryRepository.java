package ru.pet.portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.store.entity.CategoryE;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryE, UUID> {

    default CategoryE findByIdWithThrow(UUID id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Категория с идентификатором " + id + " отсутствует"));
    }
}
