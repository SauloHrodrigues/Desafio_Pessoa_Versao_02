package com.desafio_pessoas02.Pessoa02.repository;

import com.desafio_pessoas02.Pessoa02.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
