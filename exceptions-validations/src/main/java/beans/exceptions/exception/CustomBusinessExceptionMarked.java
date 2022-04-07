package beans.exceptions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "this text is not processed")
public class CustomBusinessExceptionMarked extends RuntimeException {

    public CustomBusinessExceptionMarked() {
        super("custom business exception message marked");
    }
}
