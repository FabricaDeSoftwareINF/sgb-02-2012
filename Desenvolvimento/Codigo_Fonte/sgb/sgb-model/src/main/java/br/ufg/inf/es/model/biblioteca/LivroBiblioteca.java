package br.ufg.inf.es.model.biblioteca;

/**
 * Representacao dos dados retornados da biblioteca
 *
 * @author Victor Ribeiro de Carvalho
 */
public class LivroBiblioteca {

    private static final int SALTO = 56;
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
        if (!this.quantidade.equals(other.quantidade) && this.quantidade == null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = LivroBiblioteca.SALTO * hash + (this.id != null ? this.id.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.nome != null ? this.nome.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.isbn != null ? this.isbn.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.ano != null ? this.ano.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.edicao != null ? this.edicao.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.editora != null ? this.editora.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.autor != null ? this.autor.hashCode() : 0);

        hash = LivroBiblioteca.SALTO * hash + (this.quantidade != null ? this.quantidade.hashCode() : 0);

        return hash;
    }
}
