package ru.pet.portal.store.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Answer {

    private String reply;
    private boolean correctly;
}
