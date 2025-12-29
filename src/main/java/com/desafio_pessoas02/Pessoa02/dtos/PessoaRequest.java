package com.desafio_pessoas02.Pessoa02.dtos;

import com.desafio_pessoas02.Pessoa02.model.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequest(
        String nome,
        LocalDate dataDeNascimento,
        String cpf,
        List<EnderecoRequest> enderecos
) {
}
