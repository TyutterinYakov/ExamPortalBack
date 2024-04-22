package ru.pet.portal.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Table(name = "quizzes")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class QuizE {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryE category;
    @Transient
    private long maxMarks;
    @Transient
    private long countOfQuestion;
    @Transient
    private long time;

}
