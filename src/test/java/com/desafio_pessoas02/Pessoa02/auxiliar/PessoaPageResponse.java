package com.desafio_pessoas02.Pessoa02.auxiliar;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;

import java.util.List;

public record PessoaPageResponse(
        List<PessoaResponse> content,
        int totalPages,
        long totalElements,
        int size
) {
}
