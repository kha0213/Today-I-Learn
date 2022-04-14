package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * JDBC - DataSourceUtils 사용
 */
@Slf4j
public class MemberRepositoryV3 {

    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Member save(Member member) throws SQLException {
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
            throw e;
        } finally {
            close(conn, pstmt);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = getConnection();
        try {
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
            throw e;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Member findById(Connection conn, String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
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
            throw e;
        } finally {
            close(pstmt, rs);
        }
    }



    public void update(String memberId, int changeMoney) throws SQLException {
        String sql = "update Member set money = ? where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, changeMoney);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("error:", e);
            throw e;
        } finally {
            close(conn, pstmt);
        }
    }

    public void update(Connection conn, String memberId, int changeMoney) throws SQLException {
        String sql = "update Member set money = ? where member_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, changeMoney);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("error:", e);
            throw e;
        } finally {
            JdbcUtils.closeStatement(pstmt);
        }
    }


    public void delete(String memberId) throws SQLException {
        String sql = "delete from Member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("error:", e);
            throw e;
        } finally {
            close(conn, pstmt);
        }
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(conn);
    }
    private void close(PreparedStatement pstmt, ResultSet rs) {
        close(null, pstmt, rs);
    }
    private void close(Connection conn, PreparedStatement pstmt) {
        close(conn, pstmt, null);
    }

    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();

        log.info("get connection! con={}, class={}", con, con.getClass());
        return con;
    }
}
