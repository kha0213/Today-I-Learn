package com.example.springbasic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBasicApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    /** 애플리케이션의 구성이 완료되고 아직 요청이 들어오기 전 실행 */
    @Component
    static class MyCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... args) {
            System.out.println("CommandLineRunner start");
        }
    }

    /** 애플리케이션이 완전히 준비 된 이후에 실행 */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("ApplicationReadyEvent start");
    }

}
