package hello.springtx.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceV0 {

    private final MemberRepository memberRepository;

    @Transactional
    void remit(Long fromId, Long toId, int money) {
        // from의 계좌에서 돈을 money만큼 인출하고
        memberRepository.withdraw(fromId, money);
        // to계좌에 money 만큼 입금한다
        memberRepository.deposit(toId, money);
    }
}
