package member;

/**
 * 2021-03-31
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);

    void printMembers();
}
