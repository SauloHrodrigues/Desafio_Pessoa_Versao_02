package com.desafio_pessoas02.Pessoa02.dtos;

import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record EnderecoRequest(

        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        boolean endrecoPrincipal
) {
}
