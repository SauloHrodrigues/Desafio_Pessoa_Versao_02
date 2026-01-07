package com.desafio_pessoas02.Pessoa02.dtos;

import com.desafio_pessoas02.Pessoa02.model.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto para receber um novo endereço.")
public record EnderecoRequest(

        @Schema(description = "Nome da rua ou avenida.", example = "Avenida rio sul")
        @NotBlank(message = "A rua do endereço é campo de preenchimento obrigatório.")
        String rua,

        @Schema(description = "Número.", example = "55")
        @NotBlank(message = "O número do endereço é campo de preenchimento obrigatório.")
        String numero,

        @Schema(description = "Nome do bairro", example = "Leblon")
        @NotBlank(message = "A bairro do endereço é campo de preenchimento obrigatório.")
        String bairro,

        @Schema(description = "Nome da cidade", example = "Niterói")
        @NotBlank(message = "A cidade do endereço é campo de preenchimento obrigatório.")
        String cidade,

        @Schema(description = "Nome do Estado", example = "Rio de Janeiro")
        @NotBlank(message = "A bairro do endereço é campo de preenchimento obrigatório.")
        String estado,

        @Schema(description = "CEP", example = "13091610")
        @NotBlank(message = "O CEP do endereço é campo de preenchimento obrigatório.")
        String cep,

        @Schema(description = "Esse endereço é o principal?", example = "true ou false")
        @NotBlank(message = "Esse endereço é o principal?, é campo de preenchimento obrigatório.")
        boolean enderecoPrincipal
) {
}
