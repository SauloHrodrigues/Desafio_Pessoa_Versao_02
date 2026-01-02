package com.desafio_pessoas02.Pessoa02.dtos;

import java.time.LocalDate;
import java.util.List;

public record PessoaIdadeResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataDeNascimento,
        int idade
) {
}
