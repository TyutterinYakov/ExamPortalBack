package portal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import portal.model.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
	@Query(nativeQuery=true, value="select answer_id, count_points, invalid_question, skip_question, valid_qustion from quize_statistic "
			+ "	inner join answer_quize "
			+ "	on answer_quize.answer = quize_statistic.ANSWER_ID "
			+ "	where answer_quize.quize=?1 ")
	List<ExamResult> findAllByQuizeId(Long id);
}
