package portal.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserDto {
	private Long userId;
	@Size(max = 30, min=4)
	private String userName;
	@Size(max = 30, min=2)
	@NotEmpty
	private String firstName;
	@Size(max = 30, min=2)
	@NotEmpty
	private String lastName;
	@Size(max = 40, min=4)
	@NotEmpty
	private String email;
	@Size(max = 15, min=4)
	@NotEmpty
	private String phone;
	
	public UserDto() {
		super();
	}
	public UserDto(Long userId, @Size(max = 30, min = 4) String userName,
			@Size(max = 30, min = 2) @NotEmpty String firstName, @Size(max = 30, min = 2) @NotEmpty String lastName,
			@Size(max = 40, min = 4) @NotEmpty String email, @Size(max = 15, min = 4) @NotEmpty String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
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
	
	
}
