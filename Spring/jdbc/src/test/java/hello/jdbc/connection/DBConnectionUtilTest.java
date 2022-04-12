package hello.jdbc.connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectionUtilTest {
    @Test
    @DisplayName("getConnection 테스트")
    void getConnection() {
        Connection conn = DBConnectionUtil.getConnection();
        assertThat(conn).isNotNull();

    }

}