package ru.pet.portal.configuration;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class TestDataSourceConfig {

    private EmbeddedPostgres pg;

    @Bean
    @Primary
    @SneakyThrows
    public DataSource dataSource() {
        pg = EmbeddedPostgres.start();
        return pg.getPostgresDatabase();
    }

    @PreDestroy
    @SneakyThrows
    void destroy() {
        if (pg != null) {
            pg.close();
        }
    }
}
