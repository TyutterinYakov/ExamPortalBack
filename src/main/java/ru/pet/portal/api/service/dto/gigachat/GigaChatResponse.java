package ru.pet.portal.api.service.dto.gigachat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GigaChatResponse {
    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private Message message;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Message {
            private String content;
        }
    }
}