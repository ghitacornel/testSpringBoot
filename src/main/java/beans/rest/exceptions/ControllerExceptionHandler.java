package beans.rest.exceptions;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
// logs can be added here
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ResponseEntity<>(e.getCause(), NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getCause(), NOT_FOUND);
    }

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<Object> handleCustomBusinessException(CustomBusinessException e) {
        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    /**
     * override how validation constraints in REST layer are handled
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (e.getFieldErrors().isEmpty()) {
            return super.handleMethodArgumentNotValid(e, headers, status, request);
        }
        List<FieldErrorJson> response = e.getFieldErrors().stream()
                .sorted((o1, o2) -> {
                    int i = o1.getField().compareToIgnoreCase(o2.getField());
                    if (i != 0) return i;
                    return o1.getCode().compareToIgnoreCase(o2.getCode());
                })// keep an order useful for tests
                .map(fieldError -> FieldErrorJson.builder().fieldName(fieldError.getField()).message(fieldError.getDefaultMessage()).messageCode(fieldError.getCode()).build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Value
    @Builder
    private static class FieldErrorJson {
        String fieldName;
        String message;
        String messageCode;
    }

    /**
     * override how validation constraints in SERVICE layer are handled
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {

        // can log errors here and not in the application
        log.error("validation error", e);

        List<FieldErrorJson> response = e.getConstraintViolations().stream()
                .sorted((o1, o2) -> {
                    int i = o1.getPropertyPath().toString().compareToIgnoreCase(o2.getPropertyPath().toString());
                    if (i != 0) return i;
                    return o1.getMessageTemplate().compareToIgnoreCase(o2.getMessageTemplate());
                })// keep an order useful for tests
                .map(fieldError -> FieldErrorJson.builder().fieldName(fieldError.getPropertyPath().toString()).message(fieldError.getMessage()).messageCode(fieldError.getMessageTemplate()).build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}