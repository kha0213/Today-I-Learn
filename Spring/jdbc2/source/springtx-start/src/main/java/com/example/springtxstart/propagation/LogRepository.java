package com.example.springtxstart.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Repository
public class LogRepository {
    private final EntityManager em;

    @Transactional
    public void save(Log logMessage) {
        log.info("로그 저장");
        em.persist(logMessage);

        if (logMessage.getMessage().contains("로그예외")) {
            log.info("로그 저장시 예외 발생");
            throw new RuntimeException("로그예외발생");
        }
    }

    public Optional<Log> findByMessage(String message) {
        return em.createQuery("select l from Log as l where l.message = :message", Log.class)
                .setParameter("message", message)
                .getResultList()
                .stream()
                .findAny();
    }
}
