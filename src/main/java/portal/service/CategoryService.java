package portal.service;

import java.util.List;

import portal.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public void removeCategory(Long id);
	public List<Category> listCategory();
	public Category getCategory(Long id);
}
