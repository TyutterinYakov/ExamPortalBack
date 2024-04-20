package ru.pet.portal.api.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.portal.api.service.CategoryService;
import ru.pet.portal.store.entity.Category;
import ru.pet.portal.store.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public void deleteById(UUID categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public void create(Category category) {
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void update(UUID categoryId, Category category) {
		final Category havingCategory = categoryRepository.findByIdWithThrow(categoryId);
		final String title = category.getTitle();
		if (!StringUtils.isBlank(title)) {
			havingCategory.setTitle(title);
		}

		final String description = category.getDescription();
		if (!StringUtils.isBlank(description)) {
			havingCategory.setDescription(description);
		}
	}

	@Override
	public Category getById(UUID categoryId) {
		return categoryRepository.findByIdWithThrow(categoryId);
	}

	@Override
	public List<Category> getAll(int from, int size) {
		return categoryRepository.findAll(PageRequest.of(from, size, Sort.Direction.ASC)).getContent();
	}
}
