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
    public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object not found ", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                         HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Integrity Violation", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException ex,
                                                                  HttpServletRequest request) {

        StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "", ex.getMessage(), request.getRequestURI());

        erro.setError(selecionarMensagem(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    private String selecionarMensagem(String ex){
        if (ex.startsWith("Não há itens")) {
            return "Carrinho Vazio!";
        } else if (ex.startsWith("Item extra não")) {
            return "Não há Item Principal no carrinho!";
        } else if (ex.startsWith("Quantidade inválida")) {
            return  "Quantidade do item menor ou igual a zero";
        }
        return ex;
    }
}
