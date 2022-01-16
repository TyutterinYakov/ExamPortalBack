package portal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.CategoryRepository;
import portal.model.Category;
import portal.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryDao;
	
	@Override
	public Category addCategory(Category category) {
		
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public void removeCategory(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public List<Category> listCategory() {
		return categoryDao.findAll();
	}

	@Override
	public Category getCategory(Long id) {
		
		Optional<Category> categoryOptional = categoryDao.findById(id);
		if(categoryOptional.isPresent()) {
			
			return categoryOptional.get();
		}
		return null;
	}

}
