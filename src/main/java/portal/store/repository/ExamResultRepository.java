//package portal.store.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import portal.store.entity.ExamResult;
//import portal.store.entity.QuizeEntity;
//import portal.store.entity.User;
//
//public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
//
//	List<ExamResult> findAllByQuize(QuizeEntity quize);
//
//	Optional<ExamResult> findByUserAndQuize(User user, QuizeEntity quize);
//
//	List<ExamResult> findAllByUser(User user);
//
//
////	@Query(nativeQuery=true, value="select answer_id, count_points, invalid_question, skip_question, valid_qustion from quize_statistic "
////			+ "	inner join answer_quize "
////			+ "	on answer_quize.answer = quize_statistic.ANSWER_ID "
////			+ "	where answer_quize.quize=?1 ")
////	List<ExamResult> findAllByQuizeId(Long id);
//}
