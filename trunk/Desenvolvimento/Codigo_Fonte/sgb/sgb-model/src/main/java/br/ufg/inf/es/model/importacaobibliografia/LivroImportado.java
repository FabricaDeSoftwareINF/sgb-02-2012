/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

/**
 *
 * @author Vinícius
 */
public class LivroImportado {
    
    private String titulo;
    private String edicao;
    private String anoPublicacao;
    private String autor;
    private String isbn10;
    private String isbn13;

    public LivroImportado() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    @Override
    public String toString() {
        return "LivroImportado{" + "titulo=" + titulo + ", edicao=" 
                + edicao + ", anoPublicacao=" + anoPublicacao 
                + ", autor=" + autor + ", isbn10=" + isbn10 
                + ", isbn13=" + isbn13 + '}';
    }
    
}
