package com.example.mvcseminar;

import com.example.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(UserController.class)
public class MvcSeminarApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcSeminarApplication.class, args);
    }

}
