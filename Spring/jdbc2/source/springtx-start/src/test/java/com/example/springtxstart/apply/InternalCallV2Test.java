package com.example.springtxstart.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InternalCallV2Test {

    @Autowired
    BasicService basicService;

    @Test
    void external_call() {
        boolean active = basicService.externalCallInternal();
        assertThat(active).isTrue();
    }

    @Test
    void internal_call() {
        boolean active = basicService.internal();
        assertThat(active).isTrue();
    }

    @TestConfiguration
    static class config {
        @Bean
        BasicService basicService() {
            return new BasicService(internalService());
        }

        @Bean
        InternalService internalService() {
            return new InternalService();
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class BasicService {
        private final InternalService internalService;
        public boolean externalCallInternal() {
            log.info("external service");
            return internalService.internal();
        }

        @Transactional
        public boolean internal() {
            log.info("internal service");
            return isTransactionActive();
        }

        private boolean isTransactionActive() {
            boolean active = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("active {}", active);
            return active;
        }
    }

    @Slf4j
    static class InternalService {
        @Transactional
        public boolean internal() {
            log.info("internal service");
            return isTransactionActive();
        }

        private boolean isTransactionActive() {
            boolean active = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("active {}", active);
            return active;
        }
    }



}

