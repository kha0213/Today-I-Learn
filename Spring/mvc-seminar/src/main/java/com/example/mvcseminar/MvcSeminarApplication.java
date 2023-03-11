package com.example.mvcseminar;

import com.example.spring.v0.UserControllerV0;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackageClasses = {UserControllerV0.class, MvcSeminarApplication.class})
public class MvcSeminarApplication {


    @Component
    @RequiredArgsConstructor
    static class View implements CommandLineRunner {
        private final ConditionEvaluationReport report;

        private final ApplicationContext ac;
        @Override
        public void run(String... args) throws Exception {
            long count = report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch())
                    .filter(co -> !co.getKey().contains("Jmx"))
                    .peek(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c ->
                                System.out.println("\t" + c.getCondition())
                        );
                        System.out.println();
                    }).count();
            System.out.println(count);
        }
    }

    @Autowired
    private ApplicationContext ac;
    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) {
        System.out.println("MvcSeminarApplication.onReady");
        Arrays.stream(ac.getBeanDefinitionNames())
                .filter(name -> name.contains("httpService")
                || name.contains("argumentResolver"))
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        SpringApplication.run(MvcSeminarApplication.class, args);
    }
}
