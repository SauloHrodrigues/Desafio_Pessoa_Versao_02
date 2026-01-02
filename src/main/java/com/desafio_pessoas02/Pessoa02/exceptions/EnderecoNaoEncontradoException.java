package com.desafio_pessoas02.Pessoa02.exceptions;

public class EnderecoNaoEncontradoException extends RuntimeException {
    public EnderecoNaoEncontradoException(Long id){
        super("Não foi encontrada nenhum endereço para o ID: "+id);
    }
}
