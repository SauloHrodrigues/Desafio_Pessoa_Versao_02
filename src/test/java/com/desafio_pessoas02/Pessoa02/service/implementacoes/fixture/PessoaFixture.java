package com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaFixture {

    private static final Long ID = 1L;
    private static final String NOME = "Jose Ferreira da Silva";
    private static final LocalDate DATA_NASCIMENTO = LocalDate.of(1978, 9, 12);
    private static final String CPF = "22244488866";

    public static PessoaRequest request(){
        return new PessoaRequest(
                NOME,
                DATA_NASCIMENTO,
                CPF,
                List.of(EnderecoFixture.request())
        );
    }

    public static Pessoa entity(){
        Pessoa pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO, CPF, new ArrayList<>());
        pessoa.addEndereco(EnderecoFixture.entity(pessoa));
        return pessoa;
    }

    public static PessoaResponse response(){
        return new PessoaResponse(
                ID,
                NOME,
                DATA_NASCIMENTO,
                CPF,
                List.of(EnderecoFixture.response())
        );
    }
}