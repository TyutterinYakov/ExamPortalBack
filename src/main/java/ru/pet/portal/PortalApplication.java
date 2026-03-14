package ru.pet.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class PortalApplication {

	static {
		System.setProperty("javax.net.ssl.trustStore","/Users/asatutterin/Downloads/ExamPortalBack/keystore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword","changeit");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

}
