package portal.api.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import portal.api.dto.CategoryDto;
import portal.api.dto.mapper.CategoryMapper;
import portal.api.exception.NotFoundException;
import portal.api.service.CategoryService;
import portal.store.entity.Category;
import portal.store.repository.CategoryRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryDao;
	private final CategoryMapper categoryMapper;

	@Override
	@Transactional
	public void deleteById(long categoryId) {
		categoryDao.findById(categoryId).ifPresent((c) -> categoryDao.deleteById(c.getId()));
	}

	@Override
	@Transactional
	public void add(CategoryDto categoryDto) {
		Category category = categoryMapper.toEntity(categoryDto);
		categoryDao.save(category);
	}

	@Override
	@Transactional
	public void update(CategoryDto categoryDto) {
		Category category = categoryDao.findById(categoryDto.getId()).orElseThrow(() ->
				new NotFoundException("Категория с идентификатором " + categoryDto.getId() + " не найдена"));
		String newTitle = categoryDto.getTitle();
		if (newTitle != null  && !newTitle.isBlank()) {
			category.setTitle(newTitle);
		}
		String newDescription = categoryDto.getDescription();
		category.setDescription(newDescription);
	}

	@Override
	public List<CategoryDto> getAll() {
		return categoryMapper.toDto(categoryDao.findAll());
	}

	@Override
	public CategoryDto getById(long categoryId) {
		return categoryMapper.toDto(categoryDao.findById(categoryId).orElseThrow(() ->
				new NotFoundException(String.format("Категория с идентификатором \"%s\" не найдена", categoryId))));
	}

}
