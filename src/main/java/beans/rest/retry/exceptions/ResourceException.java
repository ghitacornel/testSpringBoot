package beans.rest.retry.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class ResourceException extends RuntimeException {
    public ResourceException(String message) {
        super(message);
    }
}
