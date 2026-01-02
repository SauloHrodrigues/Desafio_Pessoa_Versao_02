package com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture;

import com.desafio_pessoas02.Pessoa02.dtos.EnderecoRequest;
import com.desafio_pessoas02.Pessoa02.dtos.EnderecoResponse;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;

public class EnderecoFixture {

    private static final Long ID = 5L;
    private static final String RUA = "Rua das Flores";
    private static final String NUMERO = "55";
    private static final String BAIRRO = "Leblon";
    private static final String CIDADE = "Rio de Janeiro";
    private static final String ESTADO = "RJ";
    private static final String CEP = "13091610";
    private static final Boolean ENDERECO_PRINCIPAL = true;

    public static EnderecoRequest request(){
        return new EnderecoRequest(
                RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, ENDERECO_PRINCIPAL
        );
    }

    public static Endereco entity(Pessoa pessoa){
        Endereco endereco = new Endereco(
                ID, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, ENDERECO_PRINCIPAL,
                pessoa
        );
        return endereco;
    }

    public static EnderecoResponse response(){
        return new EnderecoResponse(
                ID, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, ENDERECO_PRINCIPAL
        );
    }
}

