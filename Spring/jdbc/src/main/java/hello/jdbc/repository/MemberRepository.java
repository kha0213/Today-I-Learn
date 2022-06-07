package hello.jdbc.repository;

import hello.jdbc.domain.Member;

/**
 * 인터페이스
 */
public interface MemberRepository {
    Member save(Member member);

    Member findById(String memberId);

    void update(String memberId, int changeMoney);

    void delete(String memberId);
}
