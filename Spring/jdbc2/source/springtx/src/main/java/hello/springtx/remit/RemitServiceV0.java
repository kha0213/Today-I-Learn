package hello.springtx.remit;

import hello.springtx.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RemitServiceV0 {

    private final RemitRepository remitRepository;

    void remit(Member from, Member to, int money) {

        // from의 계좌에서 돈을 money만큼 인출하고
        remitRepository.withdraw(from, money);
        // to계좌에 money 만큼 입금한다
        remitRepository.deposit(to, money);
    }
}
