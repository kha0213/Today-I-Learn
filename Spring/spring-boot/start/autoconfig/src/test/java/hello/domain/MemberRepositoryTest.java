package hello.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        memberRepository.save(new Member("1", "name1"));
        memberRepository.save(new Member("2", "name2"));
        List<Member> all =
                memberRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getMemberId()).isEqualTo("1");
        assertThat(all.get(0).getName()).isEqualTo("name1");
    }

    @Test
    void findById() {
        memberRepository.save(new Member("1", "name1"));
        memberRepository.save(new Member("2", "name2"));

        Member member = memberRepository.findById("2");

        assertThat(member.getMemberId()).isEqualTo("2");
        assertThat(member.getName()).isEqualTo("name2");
    }

    @Test
    void deleteAll() {
        memberRepository.save(new Member("1", "name1"));
        memberRepository.save(new Member("2", "name2"));

        memberRepository.deleteAll();

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void count() {
        memberRepository.save(new Member("1", "name1"));
        memberRepository.save(new Member("2", "name2"));

        int count = memberRepository.count();

        assertThat(count).isEqualTo(2);
    }
}