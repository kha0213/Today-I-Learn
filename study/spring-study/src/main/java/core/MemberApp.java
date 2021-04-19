package core;

import member.Grade;
import member.Member;
import member.MemberService;
import member.MemberServiceImpl;
import repository.MemoryMemberRepository;

/**
 * 2021-03-31
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        Member memberA = new Member(1L, "MEMBER_A", Grade.BASIC);
        memberService.join(memberA);
        Member memberB = new Member(2L, "MEMBER_B", Grade.VIP);
        memberService.join(memberB);

        Member findMemberA = memberService.findMember(memberA.getId());
        Member findMemberB = memberService.findMember(memberB.getId());

        System.out.println("memberA == findMemberA : " + (memberA.equals(findMemberA)));
        System.out.println("memberB == findMemberB : " + (memberB==findMemberB));


    }
}
