package br.com.vr.controller;

import br.com.vr.dto.CreateCardDTO;
import br.com.vr.exception.CardAlreadyExistsException;
import br.com.vr.exception.CardNotFoundException;
import br.com.vr.exception.TransactionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity businessException(CardNotFoundException ex, WebRequest request) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<CreateCardDTO> badRequestHandler(CardAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<CreateCardDTO>(ex.getResponse(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> badRequestHandler(TransactionException ex, WebRequest request) {
        return new ResponseEntity<String>(ex.getStatusError().name(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
