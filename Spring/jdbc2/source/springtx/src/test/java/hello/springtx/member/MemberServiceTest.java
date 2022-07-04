package hello.springtx.member;

import hello.springtx.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberService service;
    @Test
    @DisplayName("A -> B 2000원 송금")
    void remit() {
        Member memberA = new Member("A", 10000);
        em.persist(memberA);
        Member memberB = new Member("B", 10000);
        em.persist(memberB);

        service.remit(memberA.getId(), memberB.getId(),2000);
        em.flush();
        em.clear();

        Member findMemberA = repository.findById(memberA.getId()).orElseThrow();
        Member findMemberB = repository.findById(memberB.getId()).orElseThrow();

        Assertions.assertEquals(8000, findMemberA.getSaveMoney());
        Assertions.assertEquals(12000, findMemberB.getSaveMoney());
    }
}