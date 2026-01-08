package com.desafio_pessoas02.Pessoa02.exceptions;

public class ListaVaziaException extends RuntimeException {
    public ListaVaziaException(String mensagem){
        super(mensagem);
    }
}
