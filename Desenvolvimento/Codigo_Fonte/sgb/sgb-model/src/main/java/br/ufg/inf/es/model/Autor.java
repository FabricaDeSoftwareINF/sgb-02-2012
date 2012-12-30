/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "AUTOR")
public class Autor extends AbstractEntityModel {

    @Column(name = "nome")
    private String nome;
    @ManyToMany
    @JoinTable(name = "livro_autor", joinColumns =
    @JoinColumn(name = "id_autor"), inverseJoinColumns =
    @JoinColumn(name = "id_livro"))
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
