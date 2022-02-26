package beans.rest.retry.configuration;

import beans.rest.retry.exceptions.ResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class SpecificExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ResourceException e) {
        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

}