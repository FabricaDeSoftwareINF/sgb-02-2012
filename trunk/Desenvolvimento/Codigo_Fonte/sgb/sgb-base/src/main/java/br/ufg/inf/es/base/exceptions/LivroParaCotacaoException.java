package br.ufg.inf.es.base.exceptions;

/**
 * Exceção utilizada para tratar problemas no mecanismo de busca de livros para 
 * cotacao.
 * 
 * @author Victor Carvalho
 */
public class LivroParaCotacaoException extends Exception {

    /** Campo serialVersionUID*/
	private static final long serialVersionUID = 747314825424123598L;

	/**
     * Construtor desta classe.
     * @param mensagem
     */
    public LivroParaCotacaoException(String mensagem) {
        super(mensagem);
    }
}
