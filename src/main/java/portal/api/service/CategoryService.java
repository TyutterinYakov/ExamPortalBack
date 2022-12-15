package portal.api.service;

import org.springframework.transaction.annotation.Transactional;
import portal.api.dto.CategoryDto;
import portal.api.exception.NotFoundException;
import portal.store.entity.Category;

import java.util.List;

public interface CategoryService {
    void deleteById(long categoryId);

    void add(CategoryDto categoryDto);

    void update(CategoryDto categoryDto);
    List<CategoryDto> getAll();

    CategoryDto getById(long categoryId);
}
