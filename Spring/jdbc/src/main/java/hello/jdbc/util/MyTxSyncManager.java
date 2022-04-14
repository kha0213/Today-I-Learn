package hello.jdbc.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

public class MyTxSyncManager {

    private static final MyTxSyncManager INSTANCE = new MyTxSyncManager();
    private static final ThreadLocal<Connection> conn = new ThreadLocal<>();
    private final DataSource dataSource;

    private MyTxSyncManager(){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setMaximumPoolSize(10);
        dataSource = ds;
    }

    public static MyTxSyncManager getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() throws SQLException {
        if ( conn.get() == null ) {

        }
        return null;
    }
}
