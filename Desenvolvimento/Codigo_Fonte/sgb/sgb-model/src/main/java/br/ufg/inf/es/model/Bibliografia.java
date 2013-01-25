/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Henrique, Marco Aurélio
 */
@Entity
@Table(name = "BIBLIOGRAFIA")
public class Bibliografia extends AbstractEntityModel {

    @Column(name = "tipo")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;
    @ManyToOne(targetEntity = Disciplina.class)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
