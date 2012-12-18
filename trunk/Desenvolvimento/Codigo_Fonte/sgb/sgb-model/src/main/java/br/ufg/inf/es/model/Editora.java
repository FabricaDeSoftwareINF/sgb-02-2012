/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "EDITORA")
public class Editora extends AbstractEntityModel {

    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "editora")
    private Collection<Livro> livros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Collection<Livro> livros) {
        this.livros = livros;
    }
    
}
