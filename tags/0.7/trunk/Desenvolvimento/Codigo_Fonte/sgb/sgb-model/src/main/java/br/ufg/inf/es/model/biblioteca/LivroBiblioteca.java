package br.ufg.inf.es.model.biblioteca;

/**
 * Representacao dos dados retornados da biblioteca
 *
 * @author Victor Ribeiro de Carvalho
 */
public class LivroBiblioteca {

    private Long id;
    private String nome;
    private String isbn;
    private String ano;
    private Integer edicao;
    private String editora;
    private String autor;
    private Integer quantidade;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "LivroBiblioteca{" + "id=" + id + ", nome=" + nome + ", isbn="
                + isbn + ", ano=" + ano + ", edicao=" + edicao + ", editora="
                + editora + ", autor=" + autor + ", quantidade=" + quantidade + '}';
    }  
}
