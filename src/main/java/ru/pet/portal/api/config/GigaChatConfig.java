package ru.pet.portal.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "gigachat")
public class GigaChatConfig {
    private String apiKey;
    private String model;
    private int timeout;
    private String apiUrl = "https://gigachat.devices.sberbank.ru/api/v1/chat/completions";
    private String authApiUrl = "https://ngw.devices.sberbank.ru:9443/api/v2/oauth";

    @Bean
    public RestTemplate gigachatRestTemplate() {
        // Можно добавить interceptor для установки заголовков
        return new RestTemplate();
    }
}