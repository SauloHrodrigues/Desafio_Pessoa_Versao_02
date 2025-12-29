package com.desafio_pessoas02.Pessoa02.dtos;

public record EnderecoResponse(

        Long id,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        boolean endrecoPrincipal
) {
}
