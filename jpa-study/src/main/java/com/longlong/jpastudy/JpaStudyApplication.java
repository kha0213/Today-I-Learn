package com.longlong.jpastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaStudyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JpaStudyApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
