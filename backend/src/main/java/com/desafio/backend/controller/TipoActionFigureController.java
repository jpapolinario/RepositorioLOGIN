package com.desafio.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.backend.model.TipoActionFigure;
import com.desafio.backend.repository.TipoActionFigureRepository;;

@RestController
@RequestMapping("/api/tipo-action-figure")
@SuppressWarnings("null")

public class TipoActionFigureController {
    
    private final TipoActionFigureRepository repository;

    public TipoActionFigureController(TipoActionFigureRepository repository) {
        this.repository = repository;
    }

    // 1. LISTAR TODOS
    @GetMapping
    public List<TipoActionFigure> listarTodos() {
        return repository.findAll();
    }

    // 2. CRIAR
    @PostMapping
    public TipoActionFigure criar(@RequestBody TipoActionFigure novoTipo) {
        novoTipo.setExcluido(false); // Garante que nasce não excluído
        return repository.save(novoTipo);
    }

    // 3. ALTERAR
    @PutMapping("/{id}")
    public TipoActionFigure alterar(@PathVariable Integer id, @RequestBody TipoActionFigure dadosAtualizados) {
        return repository.findById(id).map(registro -> {
            registro.setDescricao(dadosAtualizados.getDescricao());
            registro.setExcluido(dadosAtualizados.getExcluido());
            return repository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Tipo de Action Figure não encontrado com o id: " + id));
    }

    // 4. EXCLUSÃO LÓGICA
    @PutMapping("/{id}/desativar")
    public TipoActionFigure exclusaoLogica(@PathVariable Integer id) {
        return repository.findById(id).map(registro -> {
            registro.setExcluido(true); // Marca como excluído logicamente
            return repository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Tipo de Action Figure não encontrado"));
    }
    
    // 5. EXCLUSÃO FÍSICA
    @DeleteMapping("/{id}")
    public String exclusaoFisica(@PathVariable Integer id) {
        repository.deleteById(id);
        return "Tipo de Action Figure deletado com sucesso do banco de dados!";
    }

}
