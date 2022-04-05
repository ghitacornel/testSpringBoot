package beans.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such order")
public class CustomBusinessExceptionMarked extends RuntimeException {

    public CustomBusinessExceptionMarked() {
        super("custom business exception message");
    }
}
