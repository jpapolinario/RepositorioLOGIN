//É a ponte que ensina o Java a gravar e buscar essa entidade no banco de dados. a interface de comandos SQL via Spring Data JPA.
package com.desafio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.backend.model.TipoPessoa;

public interface TipoPessoaRepository extends JpaRepository<TipoPessoa, Integer> {
    // Herdando de JpaRepository, o Spring já cria sozinho os métodos:
    // save(), findAll(), findById(), deleteById(), etc.
}
