package hr.fer.infsus.staem.advice;

import hr.fer.infsus.staem.exception.EntityNotFound;
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

    @ExceptionHandler(value = { EntityNotFound.class })
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFound exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

