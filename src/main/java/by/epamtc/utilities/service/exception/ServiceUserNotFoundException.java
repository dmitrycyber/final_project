package by.epamtc.utilities.service.exception;

public class ServiceUserNotFoundException extends Exception {

	public ServiceUserNotFoundException() {
        super();
    }

    public ServiceUserNotFoundException(Exception e) {
        super(e);
    }

    public ServiceUserNotFoundException(String message, Exception e) {
        super(message, e);
    }

}
