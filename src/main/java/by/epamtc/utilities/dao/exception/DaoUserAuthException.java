package by.epamtc.utilities.dao.exception;

public class DaoUserAuthException extends Exception {

    public DaoUserAuthException() {
    }

    public DaoUserAuthException(String message) {
        super(message);
    }

    public DaoUserAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoUserAuthException(Throwable cause) {
        super(cause);
    }
}
