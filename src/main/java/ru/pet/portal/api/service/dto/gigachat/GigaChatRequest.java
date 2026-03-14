package ru.pet.portal.api.service.dto.gigachat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GigaChatRequest {
    private List<Message> messages;
    private String model;
    private Double temperature;
    private Integer maxTokens;
    private Boolean stream;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}