package com.test.toyproject1.service;

import com.test.toyproject1.entity.Member;
import com.test.toyproject1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) throws IllegalAccessException {
        validateDuplicationMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증 (이름 기반)
     * @param member
     */
    private void validateDuplicationMember(Member member) throws IllegalAccessException {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalAccessException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단 건 회원 조회 (아이디 기반)
     * @param memberId
     * @return
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
