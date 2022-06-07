package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * SqlExceptionTranslate 추가
 */
@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository {

    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;

    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = DataSourceUtils.getConnection(dataSource);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("error:", e);
            throw exTranslator.translate("save", sql, e);
        } finally {
            close(conn, pstmt);
        }
    }

    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                String id = rs.getString("member_id");
                int money = rs.getInt("money");
                return new Member(id, money);
            } else {
                throw new NoSuchElementException("Not Found member! memberId = " + memberId);
            }
        } catch (SQLException e) {
            log.error("error:", e);
            throw exTranslator.translate("findById", sql, e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void update(String memberId, int changeMoney) {
        String sql = "update Member set money = ? where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, changeMoney);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("error:", e);
            throw exTranslator.translate("update", sql, e);
        } finally {
            close(conn, pstmt);
        }
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from Member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("error:", e);
            throw exTranslator.translate("delete", sql, e);
        } finally {
            close(conn, pstmt);
        }
    }


    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        //JdbcUtils.closeConnection(conn);
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
    private void close(PreparedStatement pstmt, ResultSet rs) {
        close(null, pstmt, rs);
    }
    private void close(Connection conn, PreparedStatement pstmt) {
        close(conn, pstmt, null);
    }

    private Connection getConnection() throws SQLException {
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get connection! con={}, class={}", con, con.getClass());
        return con;
    }
}
