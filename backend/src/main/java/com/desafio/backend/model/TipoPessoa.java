//Define como os dados existem na memória e como eles espelham as colunas do PostgreSQL.
package com.desafio.backend.model;

import jakarta.persistence.Column; // Importa a configuração de colunas do banco
import jakarta.persistence.Entity; // Importa o conceito de Entidade do Banco
import jakarta.persistence.GeneratedValue; // Importa a geração automática de IDs
import jakarta.persistence.GenerationType; // Importa as estratégias de geração de ID
import jakarta.persistence.Id;     // Importa a marcação de Chave Primária
import jakarta.persistence.Table;  // Importa a configuração de tabelas

@Entity // Diz ao Spring/JPA: "Esta classe representa uma tabela no Banco de Dados"
@Table(name = "tipo_pessoa") // Define explicitamente o nome da tabela no PostgreSQL
public class TipoPessoa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 256) // Configura a coluna: nome no banco, não aceita nulo, tamanho máximo 256
    private String descricao; // email e senha

    @Column(name = "estado", nullable = false) // Mapeia o campo estado (usado para sua exclusão lógica)
    private Boolean estado;

    public TipoPessoa() { // Construtor padrão obrigatório para o JPA funcionar por trás dos panos
    }

    public TipoPessoa(Integer id, String descricao, Boolean estado) { // Construtor completo para você instanciar objetos manualmente se precisar
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override 
    public String toString() { // Avisa que estamos mudando o comportamento padrão do método toString nativo do Java
        return "Usuario{id=" + id + ", descrição=" + descricao + ", estado=" + estado + "}";
    }
}
