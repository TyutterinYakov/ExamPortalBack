package portal.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.CategoryEntity;
import portal.store.entity.QuizeEntity;

@Repository
public interface QuizeRepository extends JpaRepository<QuizeEntity, Long>{

	Optional<List<QuizeEntity>> findAllByCategoryAndActive(CategoryEntity ct, boolean tr);

	Optional<QuizeEntity> findByActiveAndQuizeId(boolean b, Long id);

	List<QuizeEntity> findAllByActive(boolean b);

	Optional<List<QuizeEntity>> findByCategory(CategoryEntity category);


	Optional<List<QuizeEntity>> findAllByCategory(CategoryEntity category);

	Optional<QuizeEntity> findByQuizeIdAndActive(Long quizeId, boolean b);

	


}
