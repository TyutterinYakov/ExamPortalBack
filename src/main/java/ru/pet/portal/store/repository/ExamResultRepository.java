package ru.pet.portal.store.repository;

import java.util.UUID;

//public interface ExamResultRepository extends JpaRepository<ExamResult, UUID> {



//	@Query(nativeQuery=true, value="select answer_id, count_points, invalid_question, skip_question, valid_qustion from quize_statistic "
//			+ "	inner join answer_quize "
//			+ "	on answer_quize.answer = quize_statistic.ANSWER_ID "
//			+ "	where answer_quize.quize=?1 ")
//	List<ExamResult> findAllByQuizeId(Long id);
//}
