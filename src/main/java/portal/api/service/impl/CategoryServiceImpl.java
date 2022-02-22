package portal.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.api.dto.CategoryDto;
import portal.api.dto.factory.CategoryDtoFactory;
import portal.api.exception.BadRequestException;
import portal.api.exception.NotFoundException;
import portal.api.service.CategoryService;
import portal.store.entity.CategoryEntity;
import portal.store.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryDao;
	private final CategoryDtoFactory categoryDtoFactory;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryDao, CategoryDtoFactory categoryDtoFactory) {
		super();
		this.categoryDao = categoryDao;
		this.categoryDtoFactory = categoryDtoFactory;
	}

	//WRITE
	
	@Override
	public void removeCategoryById(Long categoryId) {
		categoryDao.deleteById(
				getCategoryById(categoryId)
						.getCategoryId());
	}
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		return categoryDtoFactory.createCategoryDto(
				categoryDao.saveAndFlush(
						new CategoryEntity()
							.makeCategoryDto(categoryDto))
				);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		getCategoryById(categoryDto.getCategoryId());
		return categoryDtoFactory
				.createCategoryDto(
						new CategoryEntity()
							.makeCategoryDto(categoryDto));
	}
	
	//READ

	@Override
	public List<CategoryDto> getAllCategory() {
		return categoryDtoFactory.createListCategoryDto(categoryDao.findAll());
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		if(categoryId==null||categoryId==0) {
			throw new BadRequestException("Поле categoryId пусто или равно нулю");
		}
		return categoryDtoFactory
				.createCategoryDto(categoryDao.findById(categoryId).orElseThrow(()->
					new NotFoundException(
						String.format(
								"Категория с идентификатором \"%s\" не найдена", 
								categoryId)
					))
				);
	}

	@Override
	public CategoryEntity getCategoryEntityById(Long categoryId) {
		if(categoryId==null||categoryId==0) {
			throw new BadRequestException("Поле categoryId пусто или равно нулю");
		}
		return categoryDao.findById(categoryId).orElseThrow(()->
								new NotFoundException(
										String.format(
												"Категория с идентификатором \"%s\" не найдена", 
												categoryId)
									)
								);
	}


	
}
