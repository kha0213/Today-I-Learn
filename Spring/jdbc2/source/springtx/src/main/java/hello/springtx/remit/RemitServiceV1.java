package hello.springtx.remit;

import hello.springtx.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@RequiredArgsConstructor
@Service
public class RemitServiceV1 {

    private final RemitRepository remitRepository;
    private final JdbcTemplate jdbcTemplate;

    private final EntityManager em;
    /**
     * 트랜잭션까지
     * @param from
     * @param to
     * @param money
     */
    public void remit(Member from, Member to, int money) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // from의 계좌에서 돈을 money만큼 인출하고
            remitRepository.withdraw(from, money);
            // to계좌에 money 만큼 입금한다
            remitRepository.deposit(to, money);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
