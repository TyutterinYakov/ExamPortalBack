//package portal.store.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//
//@Entity
//@Table(name="question")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Question {
//
//
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
//	@Column(name="content", length=4000)
//	private String content;
//	@Column(name="image")
//	private String image;
//	private String option1; //Коллекция встроенных сущностей
//	private String option2;
//	private String option3;
//	private String option4;
//	private String answer;
//	@ManyToOne(fetch=FetchType.LAZY, optional = false)
//	@JoinColumn(name = "quiz_id", nullable = false)
//	private Quiz quiz;
//
//}
