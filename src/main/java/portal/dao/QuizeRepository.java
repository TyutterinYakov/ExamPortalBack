package portal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Category;
import portal.model.Quize;

@Repository
public interface QuizeRepository extends JpaRepository<Quize, Long>{

	Optional<List<Quize>> findAllByCategoryAndActive(Category ct, boolean tr);

	Optional<Quize> findByActiveAndQuizeId(boolean b, Long id);

	List<Quize> findAllByActive(boolean b);

	Optional<List<Quize>> findByCategory(Category category);


	Optional<List<Quize>> findAllByCategory(Category category);

	Optional<Quize> findByQuizeIdAndActive(Long quizeId, boolean b);

	


}
