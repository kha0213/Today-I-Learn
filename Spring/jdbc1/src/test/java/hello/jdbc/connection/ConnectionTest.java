package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void getConnection() throws SQLException {
        Connection conn1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection conn2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("conn1 name={}, class={}", conn1, conn1.getClass());
        log.info("conn2 name={}, class={}", conn2, conn2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        DataSource ds = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(ds);
    }

    @Test
    void hikariDataSource() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("test00111");
        useDataSource(dataSource);

        Thread.sleep(1000);
    }

    private void useDataSource(DataSource ds) throws SQLException {
        Connection conn1 = ds.getConnection();
        Connection conn2 = ds.getConnection();
        log.info("conn1 name={}, class={}", conn1, conn1.getClass());
        log.info("conn2 name={}, class={}", conn2, conn2.getClass());
    }
}
