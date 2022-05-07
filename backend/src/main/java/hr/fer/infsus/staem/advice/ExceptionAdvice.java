package hr.fer.infsus.staem.advice;

import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.exception.IdMismatchException;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Order()
@NoArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { IdMismatchException.class })
    public ResponseEntity<String> handleIdMismatchException(IdMismatchException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BindException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleConstraintViolation(BindException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getFieldErrors().forEach(error -> addError(error, errors));

        return errors;
    }

    private static void addError(FieldError error, Map<String, List<String>> errors) {
        List<String> fieldErrors = errors.computeIfAbsent(error.getField(), k -> new ArrayList<>());
        fieldErrors.add(error.getDefaultMessage());
    }

}

