package portal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.CategoryRepository;
import portal.exception.NotFoundException;
import portal.model.Category;
import portal.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryDao;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryDao) {
		super();
		this.categoryDao = categoryDao;
	}

	@Override
	public Category addCategory(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		findCategoryById(category.getCategoryId());
		return categoryDao.save(category);
	}

	@Override
	public void removeCategory(Long categoryId) {
		categoryDao.deleteById(
				findCategoryById(categoryId)
						.getCategoryId());
	}

	@Override
	public List<Category> listCategory() {
		return categoryDao.findAll();
	}

	@Override
	public Category getCategory(Long categoryId) {
		return findCategoryById(categoryId);
	}
	
	
	private Category findCategoryById(Long categoryId) {
		return categoryDao.findById(categoryId).orElseThrow(()->
		new NotFoundException(
				String.format(
						"Категория с идентификатором \"%s\" не найдена", 
						categoryId)
			)
		);
	}

}
