package com.test.toyproject1.repository;

import com.test.toyproject1.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    // autowired도 가능하다
    @PersistenceContext
    EntityManager em;

    /**
     * 회원 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원 단 건 조회 (회원 아이디로)
     * @param id
     * @return Member
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * 회원 전체 조회
     * @return
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }

    /**
     * 회원 이름으로 회원 목록 조회
     * @param name
     * @return
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member as m where m.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
