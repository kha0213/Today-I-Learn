package com.example.springtxstart.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;

@SpringBootTest
public class InitTxTest {

    @Autowired
    Hello hello;

    @Test
    void go() {

    }

    @Slf4j
    static class Hello {
        @PostConstruct
        @Transactional
        public void initV1() {
            log.info("initV1");
            boolean active = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("active {}", active);
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2() {
            log.info("initV2");
            boolean active = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("active {}", active);
        }
    }

    @TestConfiguration
    static class Config {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }



}
