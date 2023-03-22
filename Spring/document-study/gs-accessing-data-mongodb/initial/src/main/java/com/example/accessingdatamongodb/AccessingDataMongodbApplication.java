package com.example.accessingdatamongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessingDataMongodbApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AccessingDataMongodbApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}
}
