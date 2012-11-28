/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name="BIBLIOGRAFIA")
public class Bibliografia extends AbstractEntityModel{
    
    private String tipo;
    private Livro livro;
    private Disciplina disciplina;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
}
