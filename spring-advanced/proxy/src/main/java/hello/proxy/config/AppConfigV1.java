package hello.proxy.config;

import hello.proxy.app.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2021-11-22
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
@Configuration
public class AppConfigV1 {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryImpl();
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceImpl(orderRepositoryV1());
    }

    @Bean
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerImpl(orderServiceV1());
    }
}
