package com.desafio_pessoas02.Pessoa02.dtos;

public record EnderecoAtualizado(
        Long id,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        Boolean endrecoPrincipal
) {
}
