package portal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Question;
import portal.model.Quize;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	Optional<List<Question>> findAllByQuize(Quize quizeId);

	Long countByQuize(Quize quize);

}
