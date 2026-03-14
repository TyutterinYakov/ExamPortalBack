package ru.pet.portal.api.service.dto.gigachat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneratedQuiz {
    private String title;
    private String description;
    private List<GeneratedQuestion> questions;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeneratedQuestion {
        private String content;
        private List<Answer> answers;
        private int marks;
        private int time;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Answer {
            private String reply;
            private boolean correctly;
        }
    }
}