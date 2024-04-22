package ru.pet.portal.api.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.portal.api.service.CategoryService;
import ru.pet.portal.store.entity.CategoryE;
import ru.pet.portal.store.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public void deleteById(UUID categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public void create(CategoryE categoryE) {
		categoryRepository.save(categoryE);
	}

	@Override
	@Transactional
	public void update(UUID categoryId, CategoryE categoryE) {
		final CategoryE havingCategoryE = categoryRepository.findByIdWithThrow(categoryId);
		final String title = categoryE.getTitle();
		if (!StringUtils.isBlank(title)) {
			havingCategoryE.setTitle(title);
		}

		final String description = categoryE.getDescription();
		if (!StringUtils.isBlank(description)) {
			havingCategoryE.setDescription(description);
		}
	}

	@Override
	public CategoryE getById(UUID categoryId) {
		return categoryRepository.findByIdWithThrow(categoryId);
	}

	@Override
	public List<CategoryE> getAll(int from, int size) {
		return categoryRepository.findAll(PageRequest.of(from, size, Sort.by("title"))).getContent();
	}
}
