package com.desafio_pessoas02.Pessoa02.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private boolean enderecoPrincipal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonIgnore
    private Pessoa pessoa;

//    @Override
//    public String toString() {
//        return "Endereco{" +
//                "id=" + id +
//                ", rua='" + rua + '\'' +
//                ", numero='" + numero + '\'' +
//                ", bairro='" + bairro + '\'' +
//                ", cidade='" + cidade + '\'' +
//                ", estado='" + estado + '\'' +
//                ", cep='" + cep + '\'' +
//                ", enderecoPrincipal=" + enderecoPrincipal +
//                ", pessoa=" + pessoa +
//                '}';
//    }
}
