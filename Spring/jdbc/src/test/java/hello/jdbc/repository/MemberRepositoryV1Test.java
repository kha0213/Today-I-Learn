package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV1Test {

    private MemberRepositoryV1 repository;

    @BeforeEach
    void datasource() {
        // 기본 DriverManager 항상 새로운 커넥션 획득
        //DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        // Hikari
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setPoolName("히카리Pool");
        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException {
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