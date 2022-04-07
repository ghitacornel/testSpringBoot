package beans.exceptions.handler;

import beans.exceptions.exception.CustomBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class CustomBusinessControllerExceptionHandler {


    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<Object> handleCustomBusinessException(CustomBusinessException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

//    @ExceptionHandler(CustomBusinessExceptionMarked.class)
//    public ResponseEntity<Object> handleCustomBusinessExceptionMarked(CustomBusinessExceptionMarked e) {
//        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);// NOT NEEDED, automatically processed
//    }

}