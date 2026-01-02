package com.desafio_pessoas02.Pessoa02.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(Long id){
        super("Não foi encontrada nenhuma pessoa para o ID: "+id);
    }
}
