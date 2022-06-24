package com.example.springtxstart.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class TxLevelTest {

    @Autowired
    BasicService basicService;

    @Test
    void tranLevel() {
        basicService.readOnly();
        basicService.readWrite();
    }

    @TestConfiguration
    static class config {
        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Slf4j
    static class BasicService {
        @Transactional(readOnly = true)
        public void readOnly() {
            log.info("readOnly");
            printInfo();
        }

        @Transactional(readOnly = false)
        public void readWrite() {
            log.info("readWrite");
            printInfo();
        }

        private void printInfo() {
            boolean active = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("active {}", active);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("readOnly {}", readOnly);
        }
    }

}

