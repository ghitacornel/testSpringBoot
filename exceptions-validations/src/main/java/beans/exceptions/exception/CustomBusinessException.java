package beans.exceptions.exception;

public class CustomBusinessException extends RuntimeException {

    public CustomBusinessException() {
        super("custom business exception message");
    }
}
