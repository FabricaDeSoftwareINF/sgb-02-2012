/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

/**
 * Entidade LivroUmportado
 * @author Vinícius
 */
public class LivroImportado {
    
    /** Campo titulo*/
    private String titulo;
    
    /** Campo edicao*/
    private String edicao;
    
    /** Campo anoPublicacao*/
    private String anoPublicacao;
    
    /** Campo autor*/
    private String autor;
    
    /** Campo isbn10*/
    private String isbn10;
    
    /** Campo isbn13*/
    private String isbn13;

    /**
     * Construtor desta classe.
     */
    public LivroImportado() {
    }

    /**
	 * Obtém o valor do campo <code>titulo</code>
	 *
	 * @return {@link String}
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Define o campo <code>titulo</code>.
	 *
	 * @param titulo 
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtém o valor do campo <code>edicao</code>
	 *
	 * @return {@link String}
	 */
	public String getEdicao() {
		return this.edicao;
	}

	/**
	 * Define o campo <code>edicao</code>.
	 *
	 * @param edicao 
	 */
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	/**
	 * Obtém o valor do campo <code>anoPublicacao</code>
	 *
	 * @return {@link String}
	 */
	public String getAnoPublicacao() {
		return this.anoPublicacao;
	}

	/**
	 * Define o campo <code>anoPublicacao</code>.
	 *
	 * @param anoPublicacao 
	 */
	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
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
	 * Obtém o valor do campo <code>isbn10</code>
	 *
	 * @return {@link String}
	 */
	public String getIsbn10() {
		return this.isbn10;
	}

	/**
	 * Define o campo <code>isbn10</code>.
	 *
	 * @param isbn10 
	 */
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	/**
	 * Obtém o valor do campo <code>isbn13</code>
	 *
	 * @return {@link String}
	 */
	public String getIsbn13() {
		return this.isbn13;
	}

	/**
	 * Define o campo <code>isbn13</code>.
	 *
	 * @param isbn13 
	 */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/** 
	 * {@inheritDoc} 
	 */
	@Override
    public String toString() {
        return "LivroImportado{" + "titulo=" + titulo + ", edicao=" 
                + edicao + ", anoPublicacao=" + anoPublicacao 
                + ", autor=" + autor + ", isbn10=" + isbn10 
                + ", isbn13=" + isbn13 + '}';
    }
    
}
