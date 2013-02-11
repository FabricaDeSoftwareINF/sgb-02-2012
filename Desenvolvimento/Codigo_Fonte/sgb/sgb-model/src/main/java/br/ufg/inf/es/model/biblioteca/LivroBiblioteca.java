package br.ufg.inf.es.model.biblioteca;

/**
 * Representacao dos dados retornados da biblioteca
 *
 * @author Victor Ribeiro de Carvalho
 */
public class LivroBiblioteca {

    /** Campo id*/
    private Long id;
    
    /** Campo nome*/
    private String nome;
    
    /** Campo isbn*/
    private String isbn;
    
    /** Campo ano*/
    private String ano;
    
    /** Campo edicao*/
    private Integer edicao;
    
    /** Campo editora*/
    private String editora;
    
    /** Campo autor*/
    private String autor;
    
    /** Campo quantidade*/
    private Integer quantidade;
    
    /**
	 * Obtém o valor do campo <code>id</code>
	 *
	 * @return {@link Long}
	 */
	public Long getId() {
		
		return this.id;
	}

	/**
	 * Define o campo <code>id</code>.
	 *
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o valor do campo <code>nome</code>
	 *
	 * @return {@link String}
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Define o campo <code>nome</code>.
	 *
	 * @param nome 
	 */
	public void setNome(String nome) {
		this.nome = nome;
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

	/**
	 * Obtém o valor do campo <code>ano</code>
	 *
	 * @return {@link String}
	 */
	public String getAno() {
		return this.ano;
	}

	/**
	 * Define o campo <code>ano</code>.
	 *
	 * @param ano 
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * Obtém o valor do campo <code>edicao</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getEdicao() {
		return this.edicao;
	}

	/**
	 * Define o campo <code>edicao</code>.
	 *
	 * @param edicao 
	 */
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	/**
	 * Obtém o valor do campo <code>editora</code>
	 *
	 * @return {@link String}
	 */
	public String getEditora() {
		return this.editora;
	}

	/**
	 * Define o campo <code>editora</code>.
	 *
	 * @param editora 
	 */
	public void setEditora(String editora) {
		this.editora = editora;
	}

	/**
	 * Obtém o valor do campo <code>autor</code>
	 *
	 * @return {@link String}
	 */
	public String getAutor() {
		return this.autor;
	}

	/**
	 * Define o campo <code>autor</code>.
	 *
	 * @param autor 
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Obtém o valor do campo <code>quantidade</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Define o campo <code>quantidade</code>.
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
}
