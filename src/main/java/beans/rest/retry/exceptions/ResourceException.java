package beans.rest.retry.exceptions;

import lombok.experimental.StandardException;

/**
 * Integration with systems that might fail must be controlled through the usage of dedicated exceptions
 */
@StandardException
public class ResourceException extends RuntimeException {
    public ResourceException(String message) {
        super(message);
    }
}
