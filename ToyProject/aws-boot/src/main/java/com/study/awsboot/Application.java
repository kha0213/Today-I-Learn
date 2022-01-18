package com.study.awsboot;

import com.study.awsboot.aop.vo.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.run(args);

        try {
            Car car = new Car(0);
            car.getOn();
            car.run();
            car.getOut();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
