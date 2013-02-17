package br.ufg.inf.es.model.dtos;

import br.ufg.inf.es.model.AbstractEntityModel;

/**
 * Classe que representa a entidade Livro para a cotação.
 *
 * @author Victor Carvalho
 */
public class LivroParaCotacao extends AbstractEntityModel {

    /**
     * Campo quantidadeVagas
     */
    private Integer quantidadeVagas;
    /**
     * Campo parametroMec
     */
    private Integer parametroMec;
    /**
     * Campo quantidadeLivrosDisponiveis
     */
    private Integer quantidadeLivrosDisponiveis;
    /**
     * Campo quantidadeLivrosFaltando
     */
    private Integer quantidadeLivrosFaltando;
    /**
     * Campo nomeLivro
     */
    private String nomeLivro;
    /**
     * Campo isbn
     */
    private String isbn;

    /**
     * Construtor desta classe.
     */
    public LivroParaCotacao() {
    }

    /**
     * Construtor desta classe.
     *
     * @param quantidadeVagas
     * @param parametroMec
     * @param quantidadeLivrosDisponiveis
     * @param quantidadeLivrosFaltando
     * @param nomeLivro
     * @param isbn
     */
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

    /**
     * Obtém o valor do campo
     * <code>quantidadeVagas</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeVagas() {
        return this.quantidadeVagas;
    }

    /**
     * Obtém o valor do campo
     * <code>parametroMec</code>
     *
     * @return {@link Integer}
     */
    public Integer getParametroMec() {
        return this.parametroMec;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidadeLivrosDisponiveis</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeLivrosDisponiveis() {
        return this.quantidadeLivrosDisponiveis;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidadeLivrosFaltando</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeLivrosFaltando() {
        return this.quantidadeLivrosFaltando;
    }

    /**
     * Obtém o valor do campo
     * <code>nomeLivro</code>
     *
     * @return {@link String}
     */
    public String getNomeLivro() {
        return this.nomeLivro;
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
}