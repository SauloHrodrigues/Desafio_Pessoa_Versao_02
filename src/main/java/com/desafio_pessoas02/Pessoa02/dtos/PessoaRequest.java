package com.desafio_pessoas02.Pessoa02.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Objeto para receber uma nova pessoa.")
public record PessoaRequest(
        @Schema(description = "Nome da pessoa a ser cadastrada", example = "Paulo Silva")
        @NotBlank(message = "O nome da pessoa é campo de preenchimento obrigatório.")
        String nome,

        @Schema(description = "Data de nascimento.", example = "1954-07-29")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de de nascimento é campo de preenchimento obrigatório.")
        LocalDate dataDeNascimento,

        @Schema(description = "CPF da pessoa.", example = "123.456.789-09")
        @NotBlank(message = "O CPF é campo de preenchimento obrigatório.")
        @CPF(message = "CPF inválido. Informe um CPF válido.")
        String cpf,

        @Schema(
                description = "Endereços da pessoa.",
                example = """
    [
      {
        "rua": "Rua das Flores",
        "numero": "123",
        "bairro": "Centro",
        "cidade": "São Paulo",
        "estado": "SP",
        "cep": "01000-000",
        "enderecoPrincipal": true
      }
    ]
    """
        )
        @NotNull(message = "É nessessário informar pelo menos um endereço.")
        @NotEmpty(message = "É necessário informar pelo menos um endereço.")
        List<EnderecoRequest> enderecos
) {
}
