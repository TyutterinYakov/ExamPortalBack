package portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import portal.store.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
