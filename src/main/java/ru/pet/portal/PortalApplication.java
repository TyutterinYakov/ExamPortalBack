package ru.pet.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class PortalApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

}
