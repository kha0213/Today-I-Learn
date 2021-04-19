package member;

import repository.MemoryMemberRepository;

/**
 * 2021-03-31
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());



}
