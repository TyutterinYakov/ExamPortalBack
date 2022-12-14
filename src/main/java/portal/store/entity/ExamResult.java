//package portal.store.entity;
//
//
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//@Entity
//@Table(name="results")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ExamResult {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="answer_id")
//	private Long answerId;
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    private User user;
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    private Quiz quiz;
//
//}
