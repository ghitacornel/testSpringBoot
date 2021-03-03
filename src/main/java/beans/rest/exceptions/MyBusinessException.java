package beans.rest.exceptions;

public class MyBusinessException extends RuntimeException {

    public MyBusinessException(String message) {
        super(message);
    }
}
