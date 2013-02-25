package br.ufg.inf.es.base.model;

import java.io.Serializable;

/**
 * TAD que define o que define os serviços primários de uma entidade.
 *
 * @author Cézar Augusto Ferreira
 */
public interface Entity<I extends Serializable> extends Serializable {

    /**
     * Método que obtem o identificador da entidade
     *
     * @return obtem o identificador da entidade
     */
    I getId();
    
    
    /**
     * Método que verifica se o objeto é novo e não foi persistido.
     * 
     * @return <code>true</code> se objeto for novo
     */
    boolean isNew();
}
