package portal.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.QuestionEntity;
import portal.store.entity.QuizeEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{

	Optional<List<QuestionEntity>> findAllByQuize(QuizeEntity quizeId);

	Long countByQuize(QuizeEntity quize);

}
