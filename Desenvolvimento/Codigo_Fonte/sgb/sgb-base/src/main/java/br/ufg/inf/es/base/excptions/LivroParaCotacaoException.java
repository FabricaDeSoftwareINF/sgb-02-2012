package br.ufg.inf.es.base.excptions;

/**
 * Exceção utilizada para tratar problemas no mecanismo de busca de livros para 
 * cotacao.
 * 
 * @author Victor Carvalho
 */
public class LivroParaCotacaoException extends Exception {

    public LivroParaCotacaoException(String mensagem) {
        super(mensagem);
    }
}
