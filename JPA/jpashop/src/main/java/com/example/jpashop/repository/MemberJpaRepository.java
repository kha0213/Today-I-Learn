package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * JPA 코드로 작성하기
 */
@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        String sql = "select m from Member m";
        return em.createQuery(sql, Member.class)
                .getResultList();
    }

    @Override
    public List<Member> findByName(String name) {
        String sql = "select m from Member m where m.name = :name";
        return em.createQuery(sql, Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
