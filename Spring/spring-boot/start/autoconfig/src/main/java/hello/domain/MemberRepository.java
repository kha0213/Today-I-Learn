package hello.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Member member) {
        jdbcTemplate.update("INSERT INTO members VALUES (?, ?)",
                member.getMemberId(), member.getName());
    }

    public Member findById(String memberId) {
        return jdbcTemplate.queryForObject("SELECT member_id, name FROM members WHERE member_id = ?",
                BeanPropertyRowMapper.newInstance(Member.class),
                memberId);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM members");
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM members", Integer.class);
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT member_id, name FROM members",
                (rs, rowNum) -> new Member(rs.getString("member_id"), rs.getString("name")));
    }
}
