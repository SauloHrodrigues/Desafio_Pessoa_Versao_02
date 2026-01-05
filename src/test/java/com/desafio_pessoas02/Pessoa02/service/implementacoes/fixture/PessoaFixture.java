package com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaFixture {

    private static final Long ID = 1L;
    private static final String NOME = "Jose Ferreira da Silva";
    private static final LocalDate DATA_NASCIMENTO = LocalDate.of(1978, 9, 12);
    private static final String CPF = "22244488866";


    public static PessoaRequest request(Pessoa pessoa) {
        return new PessoaRequest(
                pessoa.getNome(),
                pessoa.getDataDeNascimento(),
                pessoa.getCpf(),
                EnderecoFixture.listaDeRequest(pessoa.getEnderecos())
        );
    }

    public static Pessoa entity() {
        Pessoa pessoa = Pessoa.builder()
                .id(ID)
                .nome(NOME)
                .dataDeNascimento(DATA_NASCIMENTO)
                .cpf(CPF)
                .enderecos(new ArrayList<>())
                .build();

        Endereco endereco = EnderecoFixture.entity();
        endereco.setPessoa(pessoa);
        pessoa.addEndereco(endereco);
        return pessoa;
    }

    public static PessoaResponse response(Pessoa pessoa) {

        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataDeNascimento(),
                pessoa.getCpf(),
                EnderecoFixture.listaDeResponse(pessoa.getEnderecos())

        );
    }
}