package beans.rest.exceptions;

public class CustomBusinessException extends RuntimeException {

    public CustomBusinessException() {
        super("custom business exception message");
    }
}
