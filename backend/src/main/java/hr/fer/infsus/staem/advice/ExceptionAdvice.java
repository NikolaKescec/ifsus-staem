package hr.fer.infsus.staem.advice;

import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.exception.IdMismatchException;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}

