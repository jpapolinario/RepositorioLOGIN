//model
package com.desafio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_action_figure")
public class TipoActionFigure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // primary key

    @Column(name = "descricao", nullable = false, unique = true, length = 200)
    private String descricao;

    @Column(name = "excluido", nullable = false)
    private Boolean excluido;

    public TipoActionFigure() {
    }

    public TipoActionFigure(Integer id, String descricao, Boolean excluido) {
        this.id = id;
        this.descricao = descricao;
        this.excluido = excluido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }
}