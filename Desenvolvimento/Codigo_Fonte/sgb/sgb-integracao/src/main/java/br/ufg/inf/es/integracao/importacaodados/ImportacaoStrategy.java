package br.ufg.inf.es.integracao.importacaodados;

import java.util.Collection;

/**
 *
 * @author vinicius
 */
public interface ImportacaoStrategy<E> {
    
    Collection<E> importarDados(String entidade);
    
}
