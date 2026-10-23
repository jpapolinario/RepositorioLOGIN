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

import com.desafio.backend.model.ActionFigure;
import com.desafio.backend.model.TipoActionFigure;
import com.desafio.backend.repository.ActionFigureRepository;
import com.desafio.backend.repository.TipoActionFigureRepository;

@RestController
@RequestMapping("/api/action-figure")
@SuppressWarnings("null")
public class ActionFigureController {

    private final ActionFigureRepository actionFigureRepository;
    private final TipoActionFigureRepository tipoActionFigureRepository;

    public ActionFigureController(ActionFigureRepository actionFigureRepository, TipoActionFigureRepository tipoActionFigureRepository) {
        this.actionFigureRepository = actionFigureRepository;
        this.tipoActionFigureRepository = tipoActionFigureRepository;
    }

    // 1. LISTAR TODOS OS PRODUTOS
    @GetMapping
    public List<ActionFigure> listarTodos() {
        return actionFigureRepository.findAll();
    }

    // 2. CRIAR PRODUTO (Verificando se o Tipo/Anime existe)
    @PostMapping
    public ActionFigure criar(@RequestBody ActionFigure novaActionFigure) {
        // Valida se o ID do tipo enviado no JSON realmente existe no banco
        Integer tipoId = novaActionFigure.getTipoActionFigure().getId();
        TipoActionFigure tipoExistente = tipoActionFigureRepository.findById(tipoId)
            .orElseThrow(() -> new RuntimeException("Tipo de Action Figure (Anime) com ID " + tipoId + " não encontrado!"));

        // Associa o tipo completo validado ao produto e define como ativo
        novaActionFigure.setTipoActionFigure(tipoExistente);
        novaActionFigure.setExcluido(false);

        return actionFigureRepository.save(novaActionFigure);
    }

    // 3. ALTERAR PRODUTO
    @PutMapping("/{id}")
    public ActionFigure alterar(@PathVariable Integer id, @RequestBody ActionFigure dadosAtualizados) {
        return actionFigureRepository.findById(id).map(registro -> {
            registro.setNome(dadosAtualizados.getNome());
            
            // Se o tipo também foi alterado, validamos se o novo tipo existe
            if (dadosAtualizados.getTipoActionFigure() != null) {
                Integer novoTipoId = dadosAtualizados.getTipoActionFigure().getId();
                TipoActionFigure tipoExistente = tipoActionFigureRepository.findById(novoTipoId)
                    .orElseThrow(() -> new RuntimeException("Tipo de Action Figure com ID " + novoTipoId + " não encontrado!"));
                registro.setTipoActionFigure(tipoExistente);
            }

            registro.setExcluido(dadosAtualizados.getExcluido());
            return actionFigureRepository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Action Figure não encontrada com o id: " + id));
    }

    // 4. EXCLUSÃO LÓGICA DO PRODUTO
    @PutMapping("/{id}/desativar")
    public ActionFigure exclusaoLogica(@PathVariable Integer id) {
        return actionFigureRepository.findById(id).map(registro -> {
            registro.setExcluido(true);
            return actionFigureRepository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Action Figure não encontrada"));
    }

    // 5. EXCLUSÃO FÍSICA DO PRODUTO
    @DeleteMapping("/{id}")
    public String exclusaoFisica(@PathVariable Integer id) {
        actionFigureRepository.deleteById(id);
        return "Action Figure deletada com sucesso do banco de dados!";
    }

}
