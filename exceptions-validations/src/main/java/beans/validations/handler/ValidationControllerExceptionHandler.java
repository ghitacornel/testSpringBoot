package beans.validations.handler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * override how validation constraints in REST layer are handled
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // can log errors here and not in the application
        log.error("validation error", e);

        if (e.getFieldErrors().isEmpty()) {
            return super.handleMethodArgumentNotValid(e, headers, status, request);
        }

        return new ResponseEntity<>(
                e.getFieldErrors()
                        .stream()
                        .map(fieldError -> new FieldErrorJson(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getCode()))
                        .sorted()// keep them ordered for predictability
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * override how validation constraints are handled
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {

        // can log errors here and not in the application
        log.error("validation error", e);

        return new ResponseEntity<>(
                e.getConstraintViolations().stream()
                        .map(fieldError -> new FieldErrorJson(fieldError.getPropertyPath().toString(), fieldError.getMessage(), fieldError.getMessageTemplate()))
                        .sorted()// keep them ordered for predictability
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * define custom hibernate exception handling
     */
    @ExceptionHandler(IdentifierGenerationException.class)
    public ResponseEntity<Object> handleIdentifierGenerationException(IdentifierGenerationException e) {

        // can log errors here and not in the application
        log.error("validation error", e);

        return new ResponseEntity<>(
                new FieldErrorJson("@Id", e.getMessage(), ""),
                HttpStatus.BAD_REQUEST);
    }

}