package com.desafio_pessoas02.Pessoa02.dtos;

public record EnderecoAtualizado(

        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        boolean endrecoPrincipal
) {
}
