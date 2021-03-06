package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionOfElements;

import br.ufg.inf.es.base.util.UtilObjeto;
import javax.persistence.FetchType;

/**
 * Classe que representa a entidade Livro
 *
 * @author Henrique Hirako
 */
@Entity
@Table(name = "LIVRO")
public class Livro extends AbstractEntityModel {

    private static final int HASH = 3;
    private static final int SALTO = 71;
    /**
     * Campo titulo
     */
    @Column(name = "titulo", nullable = false)
    private String titulo;
    /**
     * Campo ano
     */
    @Column(name = "ano", nullable = false)
    private Long ano;
    /**
     * Campo isbn10
     */
    @Column(name = "isbn10", unique = true)
    private String isbn10;
    /**
     * Campo isbn13
     */
    @Column(name = "isbn13", unique = true, nullable = false)
    private String isbn13;
    /**
     * Campo edicao
     */
    @Column(name = "edicao", nullable = false)
    private String edicao;
    /**
     * Campo estrangeiro
     */
    @Column(name = "estrangeiro", nullable = false)
    private boolean estrangeiro;
    /**
     * Campo editora
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_editora")
    private Editora editora;
    /**
     * Campo autores
     */
    @ManyToMany
    @JoinTable(name = "LIVRO_AUTOR", joinColumns =
    @JoinColumn(name = "id_livro"), inverseJoinColumns =
    @JoinColumn(name = "id_autor"))
    private Collection<Autor> autores = new ArrayList<Autor>();
    /**
     * Campo bibliografias
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livro")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<Bibliografia> bibliografias = new ArrayList<Bibliografia>();
    /**
     * Campo cotacoesLivro
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        mappedBy = "livro")
    private Collection<ItemListaCotacao> itensListaCotacao = new ArrayList<ItemListaCotacao>();
    
    /**
     * Campo Referencia do Livro na Biblioteca
     */
    @CollectionOfElements
    @JoinTable(name="LIVRO_REF_BIBLIOTECA")
    private Collection<Long> codigosLivrosBiblioteca = new ArrayList<Long>();
    
    /**
     * Obtém o valor do campo
     * <code>titulo</code>
     *
     * @return {@link String}
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Define o campo
     * <code>titulo</code>.
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o valor do campo
     * <code>ano</code>
     *
     * @return {@link Long}
     */
    public Long getAno() {
        return this.ano;
    }

    /**
     * Define o campo
     * <code>ano</code>.
     *
     * @param ano
     */
    public void setAno(Long ano) {
        this.ano = ano;
    }

    /**
     * Obtém o valor do campo
     * <code>isbn10</code>
     *
     * @return {@link String}
     */
    public String getIsbn10() {
        return this.isbn10;
    }

    /**
     * Define o campo
     * <code>isbn10</code>.
     *
     * @param isbn10
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * Obtém o valor do campo
     * <code>isbn13</code>
     *
     * @return {@link String}
     */
    public String getIsbn13() {
        return this.isbn13;
    }

    /**
     * Define o campo
     * <code>isbn13</code>.
     *
     * @param isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * Obtém o valor do campo
     * <code>edicao</code>
     *
     * @return {@link String}
     */
    public String getEdicao() {
        return this.edicao;
    }

    /**
     * Define o campo
     * <code>edicao</code>.
     *
     * @param edicao
     */
    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    /**
     * Obtém o valor do campo
     * <code>estrangeiro</code>
     *
     * @return {@link boolean}
     */
    public boolean isEstrangeiro() {
        return this.estrangeiro;
    }

    /**
     * Define o campo
     * <code>estrangeiro</code>.
     *
     * @param estrangeiro
     */
    public void setEstrangeiro(boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    /**
     * Obtém o valor do campo
     * <code>editora</code>
     *
     * @return {@link Editora}
     */
    public Editora getEditora() {
        return this.editora;
    }

    /**
     * Define o campo
     * <code>editora</code>.
     *
     * @param editora
     */
    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    /**
     * Obtém o valor do campo
     * <code>autores</code>
     *
     * @return {@link Collection<Autor>}
     */
    public Collection<Autor> getAutores() {
        return this.autores;
    }

    /**
     * Define o campo
     * <code>autores</code>.
     *
     * @param autores
     */
    public void setAutores(Collection<Autor> autores) {
        this.autores = autores;
    }

    /**
     * Obtém o valor do campo
     * <code>bibliografias</code>
     *
     * @return {@link Collection<Bibliografia>}
     */
    public Collection<Bibliografia> getBibliografias() {
        return this.bibliografias;
    }

    /**
     * Define o campo
     * <code>bibliografias</code>.
     *
     * @param bibliografias
     */
    public void setBibliografias(Collection<Bibliografia> bibliografias) {
        this.bibliografias = bibliografias;
    }

    /**
     * Concatena os todos os autores em uma única String separada por vírgulas.
     *
     * @return Todos os autores em uma única String separada por vírgulas
     */
    public String getAutoresAsString() {
        Collection<Autor> autoresCollection = this.getAutores();
        String autoresAsString = "";
        if (UtilObjeto.isReferencia(autoresCollection)) {
            autoresAsString = autoresCollection.toString().replace("[", "").
                    replace("]", "");
        }
        return autoresAsString;
    }

    /**
     * Concatena os todas as bibliografias em uma única String separada por
     * vírgulas. Cada bibliografia inclui o nome da disciplina, o tipo da
     * bibliografia e o curso a que pertence a disciplina.
     *
     * @return Todas as bibliografias em uma única String separada por vírgulas.
     * Cada bibliografia inclui o nome da disciplina, o tipo da bibliografia e o
     * curso a que pertence a disciplina.
     */
    public String getDisciplinasAsString() {
        Collection<Bibliografia> bibliografiasCollection = this.getBibliografias();
        StringBuilder sb = new StringBuilder();
        if (UtilObjeto.isReferencia(bibliografiasCollection)) {
            for (Bibliografia bibliografia : bibliografiasCollection) {
                sb.append(bibliografia.getDisciplina().getNome());
                sb.append(" - ");
                sb.append(bibliografia.getDisciplina().getCurso().getNome());
                sb.append(" - ");
                sb.append(bibliografia.getTipo().getValue());
            }
        }
        return sb.toString();
    }

    public Collection<ItemListaCotacao> getItensListaCotacao() {
        return itensListaCotacao;
    }

    public void setItensListaCotacao(Collection<ItemListaCotacao> cotacoesLivro) {
        this.itensListaCotacao = cotacoesLivro;
    }

    public Collection<Long> getCodigosLivrosBiblioteca() {
        return codigosLivrosBiblioteca;
    }

    public void setCodigosLivrosBiblioteca(Collection<Long> codigosLivrosBiblioteca) {
        this.codigosLivrosBiblioteca = codigosLivrosBiblioteca;
    }

       

    @Override
    public String toString() {

        return titulo;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = SALTO * hash + (this.titulo != null ? this.titulo.hashCode() : 0);
        hash = SALTO * hash + (this.ano != null ? this.ano.hashCode() : 0);
        hash = SALTO * hash + (this.isbn10 != null ? this.isbn10.hashCode() : 0);
        hash = SALTO * hash + (this.isbn13 != null ? this.isbn13.hashCode() : 0);
        hash = SALTO * hash + (this.edicao != null ? this.edicao.hashCode() : 0);
        hash = SALTO * hash + (this.estrangeiro ? 1 : 0);
        hash = SALTO * hash + (this.editora != null ? this.editora.hashCode() : 0);
        hash = SALTO * hash + (this.autores != null ? this.autores.hashCode() : 0);
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
        final Livro other = (Livro) obj;
        if ((this.titulo == null) ? (other.titulo != null) : !this.titulo.equals(other.titulo)) {
            return false;
        }
        if (this.ano != other.ano && (this.ano == null || !this.ano.equals(other.ano))) {
            return false;
        }
        if ((this.isbn10 == null) ? (other.isbn10 != null) : !this.isbn10.equals(other.isbn10)) {
            return false;
        }
        if ((this.isbn13 == null) ? (other.isbn13 != null) : !this.isbn13.equals(other.isbn13)) {
            return false;
        }
        if ((this.edicao == null) ? (other.edicao != null) : !this.edicao.equals(other.edicao)) {
            return false;
        }
        if (this.estrangeiro != other.estrangeiro) {
            return false;
        }
        if (this.editora != other.editora && (this.editora == null || !this.editora.equals(other.editora))) {
            return false;
        }
        return true;
    }
}
