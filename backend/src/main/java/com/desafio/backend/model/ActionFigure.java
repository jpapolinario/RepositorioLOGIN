//model
package com.desafio.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "action_figure")
public class ActionFigure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 243)
    private String nome;

    @ManyToOne // Várias Action Figures podem pertencer a um único Tipo de Anime
    //chave estrangeira
    @JoinColumn(name = "tipo_action_figure_id", nullable = false) // Crie a coluna de chave estrangeira no banco apontando para lá
    private TipoActionFigure tipoActionFigure;
    //Quando dizemos que o tipo de dado é `TipoActionFigure`, significa que aquela variável vai carregar consigo todas as informações daquele anime, e não apenas um número.

    @Column(name = "excluido", nullable = false)
    private Boolean excluido;

    public ActionFigure() {
    }

    public ActionFigure(Integer id, String nome, TipoActionFigure tipoActionFigure, Boolean excluido) {
        this.id = id;
        this.nome = nome;
        this.tipoActionFigure = tipoActionFigure;
        this.excluido = excluido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoActionFigure getTipoActionFigure() {
        return tipoActionFigure;
    }

    public void setTipoActionFigure(TipoActionFigure tipoActionFigure) {
        this.tipoActionFigure = tipoActionFigure;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

}