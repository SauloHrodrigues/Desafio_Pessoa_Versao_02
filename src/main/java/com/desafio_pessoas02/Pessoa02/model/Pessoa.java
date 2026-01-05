package com.desafio_pessoas02.Pessoa02.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Setter(AccessLevel.NONE)
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String cpf;
    @OneToMany(
            mappedBy = "pessoa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Endereco> enderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco){
        enderecos.add(endereco);
    }

}
