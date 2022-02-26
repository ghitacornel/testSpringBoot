package beans.rest.retry.configuration;

import beans.rest.retry.exceptions.RecoverableResourceException;
import beans.rest.retry.exceptions.UnrecoverableResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class SpecificExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecoverableResourceException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(RecoverableResourceException e) {
        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnrecoverableResourceException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(UnrecoverableResourceException e) {
        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }
}