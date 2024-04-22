package ru.pet.portal.store.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExamAnswer {
    private String givenAnswer;
    private String answer;
    private String questionContent;
}
