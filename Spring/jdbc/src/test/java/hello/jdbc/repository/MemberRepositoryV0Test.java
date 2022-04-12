package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

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