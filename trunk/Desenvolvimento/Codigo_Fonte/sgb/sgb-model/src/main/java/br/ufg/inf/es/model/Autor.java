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

    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @ManyToMany(mappedBy = "autores", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Livro> livros;

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

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

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}
