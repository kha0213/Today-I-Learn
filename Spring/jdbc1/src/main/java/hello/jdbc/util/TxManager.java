package hello.jdbc.util;

public interface TxManager {
    void begin();

    void commit();

    void rollback();
}
