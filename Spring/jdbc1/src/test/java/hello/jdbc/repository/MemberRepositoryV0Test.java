package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV0Test {

    @BeforeEach
    void before() {
        MemberRepositoryV0 repository = new MemberRepositoryV0();
    }

    @AfterEach
    void after() throws SQLException {
        MemberRepositoryV0 repository = new MemberRepositoryV0();
        repository.delete("hello1");
    }

    @Test
    void crud() throws SQLException {
        MemberRepositoryV0 repository = new MemberRepositoryV0();
        // delete
        String memberId = "hello1";
        repository.delete(memberId);
        assertThatThrownBy(() -> repository.findById(memberId))
                .isInstanceOf(NoSuchElementException.class);

        // save
        Member member = new Member(memberId, 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById(memberId);

        assertThat(member).isEqualTo(findMember);

        // update
        repository.update(memberId, 20000);
        Member updateMember = repository.findById(memberId);

        assertThat(member).isNotEqualTo(updateMember);
    }

}