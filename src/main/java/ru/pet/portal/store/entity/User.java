package ru.pet.portal.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled = true;
    private String profileImage = "default.png";
//    @Enumerated(value = EnumType.STRING)
//    private Role role;

}
