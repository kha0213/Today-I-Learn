package hello.springtx.member;

import hello.springtx.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Modifying
    @Query("update Member as m set m.saveMoney = m.saveMoney - :money where m.id = :memberId")
    void withdraw(Long memberId, Integer money);

    @Modifying
    @Query("update Member as m set m.saveMoney = m.saveMoney + :money where m.id = :memberId")
    void deposit(Long memberId, Integer money);
}
