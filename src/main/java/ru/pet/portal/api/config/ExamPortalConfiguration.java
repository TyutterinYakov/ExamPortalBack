package ru.pet.portal.api.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exam.portal")
@Data
@Slf4j
public class ExamPortalConfiguration {
    private String frontUrl;
    private String imageUploadDirectory;
    private String imageDefault;
    private String jwtHeader;
    private String jwtSecret;
    private int jwtExpiration;

    @PostConstruct
    void info() {
        log.info("With config: {}", this);
    }
}
