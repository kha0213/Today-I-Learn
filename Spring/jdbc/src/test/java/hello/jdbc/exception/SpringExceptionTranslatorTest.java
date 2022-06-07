package hello.jdbc.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SpringExceptionTranslatorTest {
    DataSource dataSource;
    @BeforeEach
    void init() {
        dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }
    @Test
    void sqlExceptionErrorCode() {
        String sql = "select bad grammar";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch (SQLException e) {
            assertThat(e.getErrorCode()).isEqualTo(42122);
            //org.springframework.jdbc.support.sql-error-codes.xml
            int errorCode = e.getErrorCode();
            log.info("errorCode={}", errorCode);
            //org.h2.jdbc.JdbcSQLSyntaxErrorException
            log.info("error", e);
            SQLErrorCodeSQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
            DataAccessException e2 = translator.translate("베드 그래마", sql, e);
            log.info("e2", e2);

            assertThat(e2.getClass()).isEqualTo(BadSqlGrammarException.class);
        }
    }
}
