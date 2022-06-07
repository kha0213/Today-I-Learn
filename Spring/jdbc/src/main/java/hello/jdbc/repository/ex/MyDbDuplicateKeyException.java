package hello.jdbc.repository.ex;

public class MyDbDuplicateKeyException extends MyDbException {
    public MyDbDuplicateKeyException() {
    }

    public MyDbDuplicateKeyException(String message) {
        super(message);
    }

    public MyDbDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDbDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
