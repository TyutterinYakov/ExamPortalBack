package portal.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portal.store.entity.Category;
import portal.store.entity.Quiz;

import java.util.List;


@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

//	List<Quiz> findAllByCategoryAndActive(Category ct, boolean active);
//
//	Quiz findByActiveAndId(boolean active, Long id);

//	List<QuizeEntity> findAllByActive(boolean b);
//
//	Optional<List<QuizeEntity>> findByCategory(CategoryEntity category);
//
//
//	Optional<List<QuizeEntity>> findAllByCategory(CategoryEntity category);
//
//	Optional<QuizeEntity> findByQuizeIdAndActive(Long quizeId, boolean b);




}
