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

}
