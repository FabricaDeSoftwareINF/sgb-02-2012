package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.*;

/**
 * Entidade Autor
 *
 * @author Henrique, Cássio Augusto
 */
@Entity
@Table(name = "AUTOR")
public class Autor extends AbstractEntityModel {

    /**
     * Campo nome
     */
    @Column(name = "nome")
    private String nome;
    /**
     * Campo sobrenome
     */
    @Column(name = "sobrenome")
    private String sobrenome;
    /**
     * Campo livros
     */
    @ManyToMany(mappedBy = "autores", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
     * <code>sobrenome</code>
     *
     * @return {@link String}
     */
    public String getSobrenome() {

        return this.sobrenome;
    }

    /**
     * Define o campo
     * <code>sobrenome</code>.
     *
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {

        this.sobrenome = sobrenome;
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
    public String toString() {
        return nome + " " + sobrenome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 97 * hash + (this.sobrenome != null ? this.sobrenome.hashCode() : 0);
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
        final Autor other = (Autor) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.sobrenome == null) ? (other.sobrenome != null) : !this.sobrenome.equals(other.sobrenome)) {
            return false;
        }
        return true;
    }
    
}
