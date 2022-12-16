package portal.store.entity;

import javax.persistence.*;

import lombok.*;


@Entity
@Table(name="quizzes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length=75, nullable = false)
	private String title;
	@Column(length=1000)
	private String description;
	@Builder.Default
	private boolean active = false;
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
