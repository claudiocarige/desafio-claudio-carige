package com.claudiocarige.desafioStartDB.resource.exceptions;

import com.claudiocarige.desafioStartDB.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.desafioStartDB.services.exceptions.IllegalArgumentException;
import com.claudiocarige.desafioStartDB.services.exceptions.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> NoSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object not found ", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Integrity Violation", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "", ex.getMessage(), request.getRequestURI());

        if (ex.getMessage().startsWith("Não há itens")){
            erro.setError("Carrinho Vazio!");
        } else if (ex.getMessage().startsWith("Item extra não")) {
            erro.setError("Não há Item Principal no carrinho!");
        } else if (ex.getMessage().startsWith("Quantidade inválida")) {
            erro.setError("Quantidade do item menor ou igual a zero");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
