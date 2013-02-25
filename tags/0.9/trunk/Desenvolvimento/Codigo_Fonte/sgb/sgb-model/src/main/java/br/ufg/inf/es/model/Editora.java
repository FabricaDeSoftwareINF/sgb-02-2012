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
 * Entidade Editora
 *
 * @author Henrique
 */
@Entity
@Table(name = "EDITORA")
public class Editora extends AbstractEntityModel {

    /**
     * Campo nome
     */
    @Column(name = "nome")
    private String nome;
    /**
     * Campo livros
     */
    @OneToMany(mappedBy = "editora")
    private Collection<Livro> livros;

    /**
     * Obtém o valor do campo
     * <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {

        return this.nome;
    }

    /**
     * Define o campo
     * <code>nome</code>.
     *
     * @param nome
     */
    public void setNome(String nome) {

        this.nome = nome;
    }

    /**
     * Obtém o valor do campo
     * <code>livros</code>
     *
     * @return {@link Collection<Livro>}
     */
    public Collection<Livro> getLivros() {

        return this.livros;
    }

    /**
     * Define o campo
     * <code>livros</code>.
     *
     * @param livros
     */
    public void setLivros(Collection<Livro> livros) {

        this.livros = livros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Editora other = (Editora) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }
}
