package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author Henrique Hirako
 */
@Entity
@Table(name = "LIVRO")
public class Livro extends AbstractEntityModel {

    @Column(name = "titulo")
    private String titulo;
    @Column(name = "ano")
    private Long ano;
    @Column(name = "isbn11")
    private String isbn11;
    @Column(name = "isbn13")
    private String isbn13;
    @Column(name = "edicao")
    private String edicao;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_editora")
    private Editora editora;
    
    @ManyToMany
    @JoinTable(name = "LIVRO_AUTOR", joinColumns =
    @JoinColumn(name = "id_livro"), inverseJoinColumns =
    @JoinColumn(name = "id_autor"))
    private Collection<Autor> autores;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livro")
    private Collection<Bibliografia> bibliografias = new ArrayList<Bibliografia>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    mappedBy = "livrosDaListaCompras",
    targetEntity = ListaCompras.class)
    private Collection<ListaCompras> listaCompras;

    public Collection<ListaCompras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(Collection<ListaCompras> listaCompras) {
        this.listaCompras = listaCompras;
    }

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

    public Collection<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Collection<Autor> autores) {
        this.autores = autores;
    }

    public Collection<Bibliografia> getBibliografias() {
        return bibliografias;
    }

    public void setBibliografias(Collection<Bibliografia> bibliografia) {
        this.bibliografias = bibliografia;
    }
        
}
