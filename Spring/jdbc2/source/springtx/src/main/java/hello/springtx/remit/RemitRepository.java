package hello.springtx.remit;

import hello.springtx.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class RemitRepository {
    public void withdraw(Member from, int money) {
    }

    public void deposit(Member to, int money) {
    }
}
