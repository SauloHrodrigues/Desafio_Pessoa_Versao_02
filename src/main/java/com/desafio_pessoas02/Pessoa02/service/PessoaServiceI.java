package com.desafio_pessoas02.Pessoa02.service;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaIdadeResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaServiceI {
    PessoaResponse criar(PessoaRequest pessoaRequest);
    Page<PessoaResponse> listar(Pageable pageable);

    PessoaResponse atualizar(Long id, PessoaAtualizada atualizacoes);

    PessoaIdadeResponse mostrarIdade(Long id);
    void apagar(Long id);
}
