package com.desafio_pessoas02.Pessoa02.integracoes_tests;

import com.desafio_pessoas02.Pessoa02.auxiliar.PessoaPageResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.service.implementacoes.fixture.PessoaFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;




@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/limpa_banco.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PessoaTestIt {

    @Autowired
    private TestRestTemplate template;


    @Test
    @DisplayName("Deve cadastrar uma pessoa com sucesso!")
    public void cadastrarUmaNovaPessoaComSucesso(){
        PessoaRequest dto = PessoaFixture.request();
        ResponseEntity<PessoaResponse> resposta = template.postForEntity(
                "/pessoas",dto,PessoaResponse.class);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertNotNull(resposta.getBody());
        assertThat(resposta.getBody().id()).isNotNull();
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());
        assertThat(resposta.getBody().cpf()).isEqualTo(dto.cpf());
        assertThat(resposta.getBody().dataDeNascimento()).isEqualTo(dto.dataDeNascimento());
        assertThat(resposta.getBody().enderecos().size()).isEqualTo(dto.enderecos().size());

    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as pessoas cadastradas no banco.")
    @Sql(scripts = {"/gera_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void listarPessoaCadastradasNoBanco() {

        ResponseEntity<PessoaPageResponse> resposta = template.exchange(
                "/pessoas",
                HttpMethod.GET,
                null,
                PessoaPageResponse.class
        );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();

        assertThat(resposta.getBody().content()).hasSize(3);
        assertThat(resposta.getBody().totalElements()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa com sucesso!")
    public void atualizaUmaPessoaComSucesso() {

        PessoaRequest dto = PessoaFixture.request();
        ResponseEntity<PessoaResponse> pessoaSalva = template.postForEntity(
                "/pessoas",dto,PessoaResponse.class);
        Long id = pessoaSalva.getBody().id();

        PessoaAtualizada atualizacao = new PessoaAtualizada(
                "maria silva", null, "99955544411", null
        );

        ResponseEntity<PessoaResponse> resposta = template.exchange(
                "/pessoas/{id}",
                HttpMethod.PATCH,
                new HttpEntity<>(atualizacao),
                PessoaResponse.class,
                id
        );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();

        assertThat(resposta.getBody().id()).isEqualTo(id);
        assertThat(resposta.getBody().nome()).isEqualTo(atualizacao.nome());
        assertThat(resposta.getBody().cpf()).isEqualTo(atualizacao.cpf());
        assertThat(resposta.getBody().dataDeNascimento()).isEqualTo(dto.dataDeNascimento());
        assertThat(resposta.getBody().enderecos()).hasSize(dto.enderecos().size());
    }


    @Test
    @DisplayName("Deve deletar uma pessoa do banco.")
    void deveDeletarUmaPessoaDoBanco() {
        PessoaRequest dto = PessoaFixture.request();
        ResponseEntity<PessoaResponse> pessoaSalva = template.postForEntity(
                "/pessoas",dto,PessoaResponse.class);
        Long id = pessoaSalva.getBody().id();

        ResponseEntity<Void> resposta = template.exchange(
                "/pessoas/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(resposta.getBody()).isNull();
    }

}
