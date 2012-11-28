package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Henrique Hirako
 */
@Entity
@Table(name = "LIVRO")
public class Livro extends AbstractEntityModel {

    private String titulo;
    private Long ano;
    private String isbn11;
    private String isbn13;
    private String edicao;
    private Editora editora;
    private Collection<Autor> autor;
    private Collection<Bibliografia> bibliografia;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public String getIsbn11() {
        return isbn11;
    }

    public void setIsbn11(String isbn11) {
        this.isbn11 = isbn11;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Collection<Autor> getAutor() {
        return autor;
    }

    public void setAutor(Collection<Autor> autor) {
        this.autor = autor;
    }

    public Collection<Bibliografia> getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(Collection<Bibliografia> bibliografia) {
        this.bibliografia = bibliografia;
    }

    
}
