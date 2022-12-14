package portal.store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="password", nullable = false)
	private String password;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name", nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	@Size(max = 15, min=4)
	@Column(nullable = false)
	private String phone;
	@Builder.Default
	private boolean enabled = true;
	@Column(nullable = false)
	@Builder.Default
	private String profileImage = "default.png";
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private Role role = Role.USER;

}
