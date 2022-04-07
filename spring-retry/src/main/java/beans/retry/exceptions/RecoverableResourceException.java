package beans.retry.exceptions;

import lombok.experimental.StandardException;

/**
 * Integration with systems that might fail must be controlled through the usage of dedicated exceptions
 */
@StandardException
public class RecoverableResourceException extends RuntimeException {
    public RecoverableResourceException(String message) {
        super(message);
    }
}
