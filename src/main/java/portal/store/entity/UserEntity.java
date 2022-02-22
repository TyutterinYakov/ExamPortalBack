package portal.store.entity;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portal.api.dto.UserDto;




@Entity
@Table(name="users")
public class UserEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	@Size(max = 30, min=4, message="Size userName min4 or max30")
	@NotEmpty
	@Column(name="user_name", unique=true)
	private String userName;
	@Column(name="password")
	private String password;
	@Size(max = 30, min=2, message="Size firstName min4 or max30")
	@NotEmpty
	@Column(name="first_name")
	private String firstName;
	@Size(max = 30, min=2, message="Size lastName min4 or max30")
	@NotEmpty
	@Column(name="last_name")
	private String lastName;
	@Size(max = 40, min=4, message="Size email min4 or max40")
	@NotEmpty
	@Column(name="email")
	private String email;
	@Size(max = 15, min=4, message="Size phone min4 or max15")
	@NotEmpty
	@Column(name="phone")
	private String phone;
	@Column(name="enabled")
	private boolean enabled=true;
	private String profile="default.png";
	@Enumerated(value = EnumType.STRING)
	private Role role = Role.USER;
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<ExamResultEntity> results = new LinkedList<>();
	
	public UserEntity() {
		super();
	}
	public UserEntity(@Size(max = 30, min = 2, message = "Size firstName min4 or max30") @NotEmpty String firstName,
			@Size(max = 30, min = 2, message = "Size lastName min4 or max30") @NotEmpty String lastName,
			@Size(max = 40, min = 4, message = "Size email min4 or max40") @NotEmpty String email,
			@Size(max = 15, min = 4, message = "Size phone min4 or max15") @NotEmpty String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	
	public List<ExamResultEntity> getResults() {
		return results;
	}
	public void setResults(List<ExamResultEntity> results) {
		this.results = results;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String toString() {
		return "userId: "+userId+" userName: "+userName+" password: "+password
				+"firstName: "+firstName+" lastName: "+lastName
				+" email: "+email+" phone: "+phone+" enabled: "+enabled
				+" profile "+profile+" role "+role.name();
	}
	
	public UserEntity makeUserDto(UserDto userDto) {
		this.email=userDto.getEmail();
		this.firstName=userDto.getFirstName();
		this.lastName=userDto.getLastName();
		this.phone=userDto.getPhone();
		return this;
	}
	
	
	
	
	
}
