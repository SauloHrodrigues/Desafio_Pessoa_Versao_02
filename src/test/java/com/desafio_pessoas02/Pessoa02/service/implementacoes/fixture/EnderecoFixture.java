package com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture;

import com.desafio_pessoas02.Pessoa02.dtos.EnderecoRequest;
import com.desafio_pessoas02.Pessoa02.dtos.EnderecoResponse;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class EnderecoFixture {

    private static final Long ID = 5L;
    private static final String RUA = "Rua das Flores";
    private static final String NUMERO = "55";
    private static final String BAIRRO = "Leblon";
    private static final String CIDADE = "Rio de Janeiro";
    private static final String ESTADO = "RJ";
    private static final String CEP = "13091610";
    private static final Boolean ENDERECO_PRINCIPAL = true;


    public static Endereco entity() {
        return Endereco.builder()
                .id(ID)
                .rua(RUA)
                .numero(NUMERO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .cep(CEP)
                .enderecoPrincipal(ENDERECO_PRINCIPAL)
                .pessoa(null)
                .build();
    }

    public static List<EnderecoRequest> listaDeRequest(List<Endereco> enderecos) {
        List<EnderecoRequest> requests = new ArrayList<>();

        for (Endereco e : enderecos) {
            requests.add(new EnderecoRequest(
                    e.getRua(),
                    e.getNumero(),
                    e.getBairro(),
                    e.getCidade(),
                    e.getEstado(),
                    e.getCep(),
                    e.isEnderecoPrincipal()
            ));
        }

        return requests;
    }

    public static List<EnderecoResponse> listaDeResponse(List<Endereco> enderecos) {
        List<EnderecoResponse> responses = new ArrayList<>();

        for (Endereco e : enderecos) {
            responses.add(new EnderecoResponse(
                    e.getId(),
                    e.getRua(),
                    e.getNumero(),
                    e.getBairro(),
                    e.getCidade(),
                    e.getEstado(),
                    e.getCep(),
                    e.isEnderecoPrincipal()
            ));
        }

        return responses;
    }
}



