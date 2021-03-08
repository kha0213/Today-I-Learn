package com.example.study_logger;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class Study01Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Study01Application.class);

		app.run(args);
		//SpringApplication.run(Study01Application.class, args);
	}

}
