package com.example.springtxstart.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        log.info("멤버 저장");
        em.persist(member);
    }

    public Optional<Member> findByUsername(String username) {
        return em.createQuery("select m from Member as m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findAny();
    }
}
