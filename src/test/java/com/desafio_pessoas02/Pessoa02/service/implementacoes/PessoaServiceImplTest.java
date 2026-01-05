package com.desafio_pessoas02.Pessoa02.service.implementacoes;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaIdadeResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import com.desafio_pessoas02.Pessoa02.repository.PessoaRepository;
import com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture.PessoaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Period;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {

    @InjectMocks
    PessoaServiceImpl service;

    @Mock
    PessoaRepository repository;

    @Mock
    private EnderecoServiceImp enderecoService;


    @Test
    @DisplayName("Deve cadastrar uma nova pessoa com sucesso.")
    void criar() {
        Pessoa pessoa = PessoaFixture.entity();
        PessoaRequest dto= PessoaFixture.request(pessoa);
        List<Endereco> enderecos = List.of(pessoa.getEnderecos().getFirst());

        when(repository.save(any(Pessoa.class))).thenReturn(pessoa);
        when(enderecoService.criar(dto.enderecos(),pessoa)).thenReturn(enderecos);

        PessoaResponse resposta = service.criar(dto);

        assertNotNull(resposta.id());
        assertEquals(dto.nome(),resposta.nome());
        assertEquals(dto.cpf(),resposta.cpf());
        assertEquals(dto.dataDeNascimento(),resposta.dataDeNascimento());
        assertEquals(dto.enderecos().size(),resposta.enderecos().size());

    }

    @Test
    @DisplayName("Deve listar todas as pessoas com sucesso.")
    void listar() {
        List<Pessoa> pessoas = List.of(PessoaFixture.entity(), PessoaFixture.entity(), PessoaFixture.entity());
        Pageable pageable = PageRequest.of(0,15);
        Page<Pessoa> pagePessoas = new PageImpl<>(pessoas,pageable,pessoas.size());

        when(repository.findAll(any(Pageable.class))).thenReturn(pagePessoas);

        Page<PessoaResponse> resposta = service.listar(pageable);

        assertEquals(3,resposta.getContent().size());
        verify(repository).findAll(pageable);
    }

    @Test
    @DisplayName("Deve atualizar dados da pessoa com sucesso.")
    void atualizar() {
        Pessoa pessoa = PessoaFixture.entity();
        PessoaAtualizada atualizacoesDto = new PessoaAtualizada("maria",null,"888.444.222-77",
        null);

        when(repository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));
        when(repository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaResponse resposta = service.atualizar(pessoa.getId(), atualizacoesDto);

        assertNotNull(resposta.id());
        assertEquals(atualizacoesDto.nome(),resposta.nome());
        assertEquals(pessoa.getDataDeNascimento(),resposta.dataDeNascimento());
        assertEquals(atualizacoesDto.cpf(),resposta.cpf());
        assertEquals(pessoa.getEnderecos().size(), resposta.enderecos().size());

    }

    @Test
    @DisplayName("Deve mostrar idade da pessoa na resposta.")
    void mostrarIdade() {
        Pessoa pessoa = PessoaFixture.entity();
        int idade = Period.between(pessoa.getDataDeNascimento(), java.time.LocalDate.now()).getYears();
        when(repository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));

        PessoaIdadeResponse resposta = service.mostrarIdade(pessoa.getId());

        assertEquals(idade, resposta.idade());

    }

    @Test
    @DisplayName("Deve apagar uma pessoa.")
    void apagar() {
        Pessoa pessoa = PessoaFixture.entity();
        Long id = pessoa.getId();

        when(repository.findById(id)).thenReturn(Optional.of(pessoa));

        service.apagar(id);

        verify(repository).delete(pessoa);
    }

}