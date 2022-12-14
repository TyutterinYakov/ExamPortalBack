package portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
