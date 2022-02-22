package portal.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.store.entity.ExamResultEntity;
import portal.store.entity.QuizeEntity;
import portal.store.entity.UserEntity;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {

	List<ExamResultEntity> findAllByQuize(QuizeEntity quize);

	Optional<ExamResultEntity> findByUserAndQuize(UserEntity user, QuizeEntity quize);

	List<ExamResultEntity> findAllByUser(UserEntity user);

	
//	@Query(nativeQuery=true, value="select answer_id, count_points, invalid_question, skip_question, valid_qustion from quize_statistic "
//			+ "	inner join answer_quize "
//			+ "	on answer_quize.answer = quize_statistic.ANSWER_ID "
//			+ "	where answer_quize.quize=?1 ")
//	List<ExamResult> findAllByQuizeId(Long id);
}
