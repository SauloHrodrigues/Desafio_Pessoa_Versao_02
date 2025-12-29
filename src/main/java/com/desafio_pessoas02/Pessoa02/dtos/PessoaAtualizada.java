package com.desafio_pessoas02.Pessoa02.dtos;

import java.time.LocalDate;
import java.util.List;

public record PessoaAtualizada(
        String nome,
        LocalDate dataDeNascimento,
        String cpf,
        List<EnderecoRequest> enderecos
) {
}
