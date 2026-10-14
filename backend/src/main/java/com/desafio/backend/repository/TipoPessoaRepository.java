package com.desafio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.backend.model.TipoPessoa;

@Repository // Diz ao Spring que esta classe cuida do acesso aos dados (Banco de Dados)
public interface TipoPessoaRepository extends JpaRepository<TipoPessoa, Long> {
    // Herdando de JpaRepository, o Spring já cria sozinho os métodos:
    // save(), findAll(), findById(), deleteById(), etc.
}
