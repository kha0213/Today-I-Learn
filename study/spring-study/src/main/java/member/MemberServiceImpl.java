package member;

import repository.MemberRepository;

/**
 * 2021-03-31
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void printMembers() {
        memberRepository.print();
    }
}
