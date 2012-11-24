/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cezar
 */
@Entity
@Table(name = "sgb_livros")
public class Livro extends AbstractEntityModel{
    @OrderingProperty(sortOrder = SortOrder.ASC)
    private String nome;
    private String autor;
    private String edicao;
    private Long ano;   
    private Collection<Disciplina> disciplinas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    
    
    
}
