package ru.pet.portal.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean enabled;
    private String profileImage;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;

}
