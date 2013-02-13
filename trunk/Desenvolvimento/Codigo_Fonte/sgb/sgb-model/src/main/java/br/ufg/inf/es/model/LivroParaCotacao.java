package br.ufg.inf.es.model;

/**
 * Classe que representa a entidade Livro para a cotação.
 * @author Victor Carvalho
 */
public class LivroParaCotacao extends AbstractEntityModel {
	
    /** Campo quantidadeVagas*/
    private Integer quantidadeVagas;
    
    /** Campo parametroMec*/
    private Integer parametroMec;
    
    /** Campo quantidadeLivrosDisponiveis*/
    private Integer quantidadeLivrosDisponiveis;
    
    /** Campo quantidadeLivrosFaltando*/
    private Integer quantidadeLivrosFaltando;
    
    /** Campo nomeLivro*/
    private String nomeLivro;
    
    /** Campo isbn*/
    private String isbn;
    
    /**
     * Construtor desta classe.
     */
    public LivroParaCotacao() {}

    /**
     * Construtor desta classe.
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
	 * Obtém o valor do campo <code>quantidadeVagas</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getQuantidadeVagas() {
		return this.quantidadeVagas;
	}

	/**
	 * Define o campo <code>quantidadeVagas</code>.
	 *
	 * @param quantidadeVagas 
	 */
	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	/**
	 * Obtém o valor do campo <code>parametroMec</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getParametroMec() {
		return this.parametroMec;
	}

	/**
	 * Define o campo <code>parametroMec</code>.
	 *
	 * @param parametroMec 
	 */
	public void setParametroMec(Integer parametroMec) {
		this.parametroMec = parametroMec;
	}

	/**
	 * Obtém o valor do campo <code>quantidadeLivrosDisponiveis</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getQuantidadeLivrosDisponiveis() {
		return this.quantidadeLivrosDisponiveis;
	}

	/**
	 * Define o campo <code>quantidadeLivrosDisponiveis</code>.
	 *
	 * @param quantidadeLivrosDisponiveis 
	 */
	public void setQuantidadeLivrosDisponiveis(Integer quantidadeLivrosDisponiveis) {
		this.quantidadeLivrosDisponiveis = quantidadeLivrosDisponiveis;
	}

	/**
	 * Obtém o valor do campo <code>quantidadeLivrosFaltando</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getQuantidadeLivrosFaltando() {
		return this.quantidadeLivrosFaltando;
	}

	/**
	 * Define o campo <code>quantidadeLivrosFaltando</code>.
	 *
	 * @param quantidadeLivrosFaltando 
	 */
	public void setQuantidadeLivrosFaltando(Integer quantidadeLivrosFaltando) {
		this.quantidadeLivrosFaltando = quantidadeLivrosFaltando;
	}

	/**
	 * Obtém o valor do campo <code>nomeLivro</code>
	 *
	 * @return {@link String}
	 */
	public String getNomeLivro() {
		return this.nomeLivro;
	}

	/**
	 * Define o campo <code>nomeLivro</code>.
	 *
	 * @param nomeLivro 
	 */
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	/**
	 * Obtém o valor do campo <code>isbn</code>
	 *
	 * @return {@link String}
	 */
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * Define o campo <code>isbn</code>.
	 *
	 * @param isbn 
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
