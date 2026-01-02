package com.desafio_pessoas02.Pessoa02.controller;

import com.desafio_pessoas02.Pessoa02.dtos.PessoaAtualizada;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaIdadeResponse;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaRequest;
import com.desafio_pessoas02.Pessoa02.dtos.PessoaResponse;
import com.desafio_pessoas02.Pessoa02.service.PessoaServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController implements PessoaSwaggerI {
    private final PessoaServiceI serviceI;

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@RequestBody @Valid PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceI.criar(pessoaRequest));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> retornarTodasPessoas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.listar(pageable));
    }

    @GetMapping("/{id}/mostrar_idade")
    public ResponseEntity<PessoaIdadeResponse> retornarIdade(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.mostrarIdade(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaAtualizada atualizacoes) {
        System.out.println("ATUALIZA");
        return ResponseEntity.status(HttpStatus.OK).body(serviceI.atualizar(id, atualizacoes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        serviceI.apagar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
