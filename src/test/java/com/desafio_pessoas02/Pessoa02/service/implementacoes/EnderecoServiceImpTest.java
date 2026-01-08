package com.desafio_pessoas02.Pessoa02.service.implementacoes;

import com.desafio_pessoas02.Pessoa02.dtos.EnderecoAtualizado;
import com.desafio_pessoas02.Pessoa02.dtos.EnderecoRequest;
import com.desafio_pessoas02.Pessoa02.exceptions.ListaVaziaException;
import com.desafio_pessoas02.Pessoa02.model.Endereco;
import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import com.desafio_pessoas02.Pessoa02.repository.EnderecoRepository;
import com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture.EnderecoFixture;
import com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture.PessoaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceImpTest {

    @InjectMocks
    private EnderecoServiceImp service;

    @Mock
    private EnderecoRepository repository;


    @Test
    @DisplayName("Deve cadastrar um Endereços com sucesso.")
    void testCriar() {
        Pessoa pessoa = PessoaFixture.entity();
        Endereco endereco = EnderecoFixture.entity();

        when(repository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco resposta = service.criar(endereco,pessoa);

        assertNotNull(resposta);
        assertEquals(resposta.getPessoa().getNome(),pessoa.getNome());
    }

    @Test
    @DisplayName("Deve cadastrar uma lista de Endereços com sucesso.")
    void criarListaDeEnderecos() {
        Pessoa pessoa = PessoaFixture.entity();
        List<EnderecoRequest> enderecoRequests = List.of(EnderecoFixture.request(), EnderecoFixture.request());

        when(repository.save(any(Endereco.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Endereco> resposta = service.criar(enderecoRequests,pessoa);

        ArgumentCaptor<Endereco> captor = ArgumentCaptor.forClass(Endereco.class);

        assertNotNull(resposta);
        assertEquals(resposta.size(),enderecoRequests.size());
        assertThat(resposta).hasSize(2);
        assertThat(resposta.get(0).getPessoa()).isNotNull();
        assertThat(resposta.get(1).getPessoa()).isNotNull();
        verify(repository, times(2)).save(captor.capture());
    }


    @Test
    @DisplayName("Deve atualizar um Endereço com sucesso.")
    void atualiza() {
        Endereco endereco = EnderecoFixture.entity();
        EnderecoAtualizado atualizacoes = new EnderecoAtualizado(endereco.getId(), "Avenida Paulista",
                null,"Penha",null,"SE","13222666",false );
        List<EnderecoAtualizado> enderecoAtualizados = List.of(atualizacoes);

        when(repository.findById(endereco.getId())).thenReturn(Optional.of(endereco));

        service.atualiza(enderecoAtualizados);

      verify(repository).save(endereco);

    }


    @Test
    @DisplayName("Deve apagar um endereco.")
    void apagar() {
        Endereco endereco = EnderecoFixture.entity();
        Long id = endereco.getId();
        List<Endereco>enderecos = List.of(endereco);

        service.apagar(enderecos);

        verify(repository).delete(endereco);
    }

    @Test
    @DisplayName("Deve validar que a lista de enderecos request não é vazia.")
    void deveValidaListaNaoVazia() {
        EnderecoRequest request = EnderecoFixture.request();
        List<EnderecoRequest> enderecoRequests = List.of(request);

        assertDoesNotThrow(() -> service.validaLista(enderecoRequests));

    }

    @Test
    @DisplayName("Deve Lancar Excecao de lista de enderecos request vazia.")
    void deveLancarExcecaoDeListaVazia() {
        EnderecoRequest request = EnderecoFixture.request();
        List<EnderecoRequest> enderecoRequests = new ArrayList<>();

        ListaVaziaException excecao = assertThrows(ListaVaziaException.class,
                ()-> service.validaLista(enderecoRequests));

        assertEquals("Lista vazia",excecao.getMessage());

    }

    @Test
    @DisplayName("Deve buscar um enderco pelo id no banco.")
    void buscar() {
        Endereco endereco = EnderecoFixture.entity();
        Long id = endereco.getId();

        when(repository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco resposta = service.buscar(id);

        assertEquals(endereco.getId(),resposta.getId());
        assertEquals(endereco.getRua(),resposta.getRua());
        assertEquals(endereco.getNumero(),resposta.getNumero());
        assertEquals(endereco.getCep(),resposta.getCep());
        assertEquals(endereco.getBairro(),resposta.getBairro());
        assertEquals(endereco.getCidade(),resposta.getCidade());
        assertEquals(endereco.getEstado(),resposta.getEstado());
    }
}