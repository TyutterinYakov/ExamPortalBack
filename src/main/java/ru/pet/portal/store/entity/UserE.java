package ru.pet.portal.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static java.util.Collections.emptySet;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserE implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled = true;
    private String profileImage = "default.png";
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @Convert(converter = StringSetConverter.class)
    private Set<String> interests;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_positions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private List<PositionE> positions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> role.name());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
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


    @Converter
    public static class StringSetConverter implements AttributeConverter<Set<String>, String> {
        private static final String SPLIT_CHAR = ";";

        @Override
        public String convertToDatabaseColumn(Set<String> stringSet) {
            return stringSet != null ? String.join(SPLIT_CHAR, stringSet) : "";
        }


        @Override
        public Set<String> convertToEntityAttribute(String string) {
            return string != null ? new HashSet<>(Arrays.asList(string.split(SPLIT_CHAR))) : emptySet();
        }
    }
}
