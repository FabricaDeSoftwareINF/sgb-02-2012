/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name="BIBLIOGRAFIA")
public class Bibliografia implements Serializable{
    
    @Column(name="tipo")
    private String tipo;
    
    @Id
    @ManyToOne
    @JoinColumn(name="id_livro")    
    private Livro livro;
    
    @Id
    @ManyToOne
    @JoinColumn(name="id_disciplina")
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
