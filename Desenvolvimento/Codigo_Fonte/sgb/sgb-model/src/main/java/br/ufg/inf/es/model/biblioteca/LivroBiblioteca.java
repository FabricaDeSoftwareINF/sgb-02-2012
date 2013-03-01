package br.ufg.inf.es.model.biblioteca;

import java.io.Serializable;

/**
 * Representacao dos dados retornados da biblioteca
 *
 * @author Victor Ribeiro de Carvalho
 */
public class LivroBiblioteca implements Serializable {
    private static final int MAX_SHORT = 100;
    /**
     * Campo id
     */
    private Long id;
    /**
     * Campo nome
     */
    private String nome;
    /**
     * Campo isbn
     */
    private String isbn;
    /**
     * Campo ano
     */
    private String ano;
    /**
     * Campo edicao
     */
    private String edicao;
    /**
     * Campo editora
     */
    private String editora;
    /**
     * Campo autor
     */
    private String autor;
    /**
     * Campo quantidade
     */
    private Integer quantidade;

    /**
     * Obtém o valor do campo
     * <code>id</code>
     *
     * @return {@link Long}
     */
    public Long getId() {

        return this.id;
    }

    /**
     * Define o campo
     * <code>id</code>.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Obtém o valor do campo
     * <code>nome</code>
     *
     * @return {@link String}
     */
    public String getShortNome() {
        String nomeCurto = nome;
        if(nome.length()> MAX_SHORT){
            nomeCurto = this.nome.substring(0, MAX_SHORT);
        }
        return nomeCurto;
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
     * <code>isbn</code>
     *
     * @return {@link String}
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Define o campo
     * <code>isbn</code>.
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtém o valor do campo
     * <code>ano</code>
     *
     * @return {@link String}
     */
    public String getAno() {
        return this.ano;
    }

    /**
     * Define o campo
     * <code>ano</code>.
     *
     * @param ano
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * Obtém o valor do campo
     * <code>edicao</code>
     *
     * @return {@link Integer}
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
     * <code>editora</code>
     *
     * @return {@link String}
     */
    public String getEditora() {
        return this.editora;
    }

    /**
     * Define o campo
     * <code>editora</code>.
     *
     * @param editora
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Obtém o valor do campo
     * <code>autor</code>
     *
     * @return {@link String}
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Define o campo
     * <code>autor</code>.
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidade</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidade() {
        return this.quantidade;
    }

    /**
     * Define o campo
     * <code>quantidade</code>.
     *
     * @param quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "LivroBiblioteca{" + "id=" + id + ", nome=" + nome + ", isbn="
                + isbn + ", ano=" + ano + ", edicao=" + edicao + ", editora="
                + editora + ", autor=" + autor + ", quantidade=" + quantidade + '}';
    }

    public String getNomeMax() {
        final int INICIO_TEXTO = 0;
        final int TAMANHO_MAX_TITULO = 50;
        if (nome != null && nome.length() > TAMANHO_MAX_TITULO) {
            return nome.substring(INICIO_TEXTO, TAMANHO_MAX_TITULO);
        } else {
            return nome;
        }
    }
    
    public String getDescricaoLivro() {
        final int INICIO_TEXTO = 0;
        final int TAMANHO_MAX_TITULO = 50;
        StringBuffer descricao = new StringBuffer();

        if (nome != null) {
            if (nome.length() > TAMANHO_MAX_TITULO) {
                descricao.append("Titulo: ").append(nome.substring(INICIO_TEXTO, TAMANHO_MAX_TITULO));
            } else {
                descricao.append("Titulo: ").append(nome);
            }
        }

        if (isbn != null) {
            descricao.append(", ISBN: ").append(isbn);
        }
        
        if (autor != null) {
            descricao.append(", Autor: ").append(autor);
        }

        if (ano != null) {
            descricao.append(", Ano: ").append(ano);
        }

        return descricao.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 67 * hash + (this.isbn != null ? this.isbn.hashCode() : 0);
        hash = 67 * hash + (this.ano != null ? this.ano.hashCode() : 0);
        hash = 67 * hash + (this.edicao != null ? this.edicao.hashCode() : 0);
        hash = 67 * hash + (this.editora != null ? this.editora.hashCode() : 0);
        hash = 67 * hash + (this.autor != null ? this.autor.hashCode() : 0);
        hash = 67 * hash + (this.quantidade != null ? this.quantidade.hashCode() : 0);
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
        final LivroBiblioteca other = (LivroBiblioteca) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.isbn == null) ? (other.isbn != null) : !this.isbn.equals(other.isbn)) {
            return false;
        }
        if ((this.ano == null) ? (other.ano != null) : !this.ano.equals(other.ano)) {
            return false;
        }
        if (this.edicao != other.edicao && (this.edicao == null || !this.edicao.equals(other.edicao))) {
            return false;
        }
        if ((this.editora == null) ? (other.editora != null) : !this.editora.equals(other.editora)) {
            return false;
        }
        if ((this.autor == null) ? (other.autor != null) : !this.autor.equals(other.autor)) {
            return false;
        }
        if (this.quantidade != other.quantidade && (this.quantidade == null || !this.quantidade.equals(other.quantidade))) {
            return false;
        }
        return true;
    }   
}
