package ru.pet.portal.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
}
