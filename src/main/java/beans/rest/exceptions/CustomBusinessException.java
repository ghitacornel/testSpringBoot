package beans.rest.exceptions;

public class CustomBusinessException extends RuntimeException {

    public CustomBusinessException(String message) {
        super(message);
    }
}
