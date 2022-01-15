package portal.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="users")
public class User implements UserDetails{
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
	@Size(max = 30, min=4, message="Size firstName min4 or max30")
	@NotEmpty
	@Column(name="first_name")
	private String firstName;
	@Size(max = 30, min=4, message="Size lastName min4 or max30")
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
	private String profile;
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
                    CascadeType.MERGE
                    
        }, fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "user_id")},
					inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="role_id")}
			)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    
    
    
    
    
    
	public User() {
		super();
	}
	public User(Long userId, String userName, String password, String firstName, String lastName, String email,
			String phone, boolean enabled, String profile, Set<Role> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.roles = roles;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
	@Override
	//@JsonIgnore 
	//TODO
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		
		this.roles.forEach(r->{
			System.out.println(r.getRoleName());
			set.add(new Authority(r.getRoleName()));
		});

		return set;
	}
	@Override
	public String getUsername() {
		return this.userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	
	
	
}
