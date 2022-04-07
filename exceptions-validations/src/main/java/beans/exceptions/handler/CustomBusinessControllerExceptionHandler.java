package beans.exceptions.handler;

import beans.exceptions.exception.CustomBusinessException;
import beans.exceptions.exception.CustomBusinessExceptionMarked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class CustomBusinessControllerExceptionHandler {


    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<Object> handleCustomBusinessException(CustomBusinessException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(CustomBusinessExceptionMarked.class)
    public ResponseEntity<Object> handleCustomBusinessExceptionMarked(CustomBusinessExceptionMarked e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

}