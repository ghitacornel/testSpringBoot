package beans.rest.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleException(EmptyResultDataAccessException e) {
        // better logs here
        return new ResponseEntity<>(e.getCause(), NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleException(NoSuchElementException e) {
        // better logs here
        return new ResponseEntity<>(e.getCause(), NOT_FOUND);
    }

}