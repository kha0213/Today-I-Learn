package repository;

import member.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021-03-31
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class MemoryMemberRepository implements MemberRepository {
    // 저장소 참고로 HashMap은 동시성 이슈 발생가능
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public void print() {
        store.forEach(
                (aLong, member) -> System.out.println(member)
        );
    }
}
