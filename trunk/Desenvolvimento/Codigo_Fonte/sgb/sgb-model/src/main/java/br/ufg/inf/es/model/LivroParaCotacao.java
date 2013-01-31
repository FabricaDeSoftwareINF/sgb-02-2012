package br.ufg.inf.es.model;

/**
 *
 * @author Victor Carvalho
 */
public class LivroParaCotacao extends AbstractEntityModel {
    private Integer quantidadeVagas;
    private Integer parametroMec;
    private Integer quantidadeLivrosDisponiveis;
    private Integer quantidadeLivrosFaltando;
    private String nomeLivro;
    private String isbn;
    
    public LivroParaCotacao() {}

    public LivroParaCotacao(Integer quantidadeVagas, Integer parametroMec,
            Integer quantidadeLivrosDisponiveis, Integer quantidadeLivrosFaltando,
            String nomeLivro, String isbn) {
        this.quantidadeVagas = quantidadeVagas;
        this.parametroMec = parametroMec;
        this.quantidadeLivrosDisponiveis = quantidadeLivrosDisponiveis;
        this.quantidadeLivrosFaltando = quantidadeLivrosFaltando;
        this.nomeLivro = nomeLivro;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public Integer getParametroMec() {
        return parametroMec;
    }

    public Integer getQuantidadeLivrosDisponiveis() {
        return quantidadeLivrosDisponiveis;
    }

    public Integer getQuantidadeLivrosFaltando() {
        return quantidadeLivrosFaltando;
    }

    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }
}
