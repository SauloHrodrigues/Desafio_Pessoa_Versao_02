package com.desafio_pessoas02.Pessoa02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<Object> handlerPessoaNaoEncontradaException(PessoaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( ex.getMessage());
    }
    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public ResponseEntity<Object> handlerEnderecoNaoEncontradoException(EnderecoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( ex.getMessage());
    }
}
