//as portas de entrada da API REST e os verbos HTTP.
package com.desafio.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.backend.model.TipoPessoa;
import com.desafio.backend.repository.TipoPessoaRepository;

@RestController // Diz que esta classe é um Controlador de API Rest (retorna JSON automaticamente)
@RequestMapping("/api/tipo-pessoa") // Define a URL base (Endpoint principal) para este controlador
@SuppressWarnings("null")
public class TipoPessoaController {

    @Autowired // Injeção de Dependência: O Spring entrega o Repository pronto para uso aqui dentro
    private TipoPessoaRepository repository;

    // 1. ENDPOINT: LISTAR TODOS (GET http://localhost:8080/api/tipo-pessoa)
    @GetMapping
    public List<TipoPessoa> listarTodos() {
        return repository.findAll(); // Busca todos os registros no PostgreSQL
    }

    // 2. ENDPOINT: CRIAR (POST http://localhost:8080/api/tipo-pessoa)
    @PostMapping
    public TipoPessoa criar(@RequestBody TipoPessoa novoTipo) {
        // @RequestBody captura o JSON enviado pelo front-end e transforma em um objeto TipoPessoa
        novoTipo.setEstado(true); // Garante que nasce ativo/excluído=falso
        return repository.save(novoTipo); // Salva no banco e retorna o objeto com o ID gerado
    }

    // 3. ENDPOINT: ALTERAR (PUT http://localhost:8080/api/tipo-pessoa/5)
    @PutMapping("/{id}")
    public TipoPessoa alterar(@PathVariable Integer id, @RequestBody TipoPessoa dadosAtualizados) {
        // @PathVariable captura o ID que veio na URL (ex: .../tipo-pessoa/5)
        return repository.findById(id).map(registro -> {
            registro.setDescricao(dadosAtualizados.getDescricao());
            registro.setEstado(dadosAtualizados.getEstado());
            return repository.save(registro); // Atualiza os dados no banco
        }).orElseThrow(() -> new RuntimeException("Registro não encontrado com o id: " + id));
    }

    // 4. ENDPOINT: EXCLUSÃO LÓGICA (PUT http://localhost:8080/api/tipo-pessoa/5/desativar)
    // Não apaga do banco, apenas muda o 'estado' para falso (excluído)
    @PutMapping("/{id}/desativar")
    public TipoPessoa exclusaoLogica(@PathVariable Integer id) {
        return repository.findById(id).map(registro -> {
            registro.setEstado(false); // Inativa o registro (Exclusão Lógica)
            return repository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }

    // 5. ENDPOINT: EXCLUSÃO FÍSICA (DELETE http://localhost:8080/api/tipo-pessoa/5)
    // Apaga definitivamente o registro do banco de dados
    @DeleteMapping("/{id}")
    public String exclusaoFisica(@PathVariable Integer id) {
        repository.deleteById(id); // Remove fisicamente do banco
        return "Registro deletado com sucesso do banco de dados!";
    }
}
