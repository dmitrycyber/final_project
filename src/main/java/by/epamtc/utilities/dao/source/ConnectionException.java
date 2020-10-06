package by.epamtc.utilities.dao.source;

public class ConnectionException extends Exception {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionException(Exception e) {
        super(e);
    }
}
