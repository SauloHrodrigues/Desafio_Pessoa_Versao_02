package com.desafio_pessoas02.Pessoa02.controller;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaIdadeResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pessoa", description = "Endpoints para gerenciar pessoas")
public interface PessoaSwaggerI {
    @Operation(
            summary = "Criar uma nova pessoa",
            description = "Cria uma nova pessoa com seus dados básicos e endereços"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",
                    content = @Content(schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    ResponseEntity<PessoaResponse> criar(
            @RequestBody @Valid PessoaRequest pessoaRequest
    );


    @Operation(
            summary = "Listar pessoas",
            description = "Retorna uma lista paginada de pessoas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
    })
    ResponseEntity<Page<PessoaResponse>> retornarTodasPessoas(
            @ParameterObject Pageable pageable
    );


    @Operation(
            summary = "Mostrar idade da pessoa",
            description = "Retorna a idade de uma pessoa com base na data de nascimento"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Idade calculada com sucesso",
                    content = @Content(schema = @Schema(implementation = PessoaIdadeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    })
    ResponseEntity<PessoaIdadeResponse> retornarIdade(
            @Parameter(description = "ID da pessoa") Long id
    );


    @Operation(
            summary = "Atualizar pessoa",
            description = "Atualiza os dados de uma pessoa existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    })
    ResponseEntity<PessoaResponse> atualizarPessoa(
            @Parameter(description = "ID da pessoa") Long id,
            PessoaAtualizada atualizacoes
    );


    @Operation(
            summary = "Excluir pessoa",
            description = "Remove uma pessoa pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    })
    ResponseEntity<Void> deletar(
            @Parameter(description = "ID da pessoa") Long id
    );
}
