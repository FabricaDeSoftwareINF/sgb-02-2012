package br.ufg.inf.es.base.controller.form;

import br.ufg.inf.es.base.model.Entity;
import java.io.Serializable;
import java.util.Collection;

/**
 * Interface que define um formulário para armazenamento de dados da interface utilizado pelo {@link Controller}}
 * 
 * @author Cézar Augusto Ferreira
 */
public interface Form<E extends Entity> extends Serializable {
    
    /**
     * Método que obtém a entidade padrão do formulário.
     *
     * @return {@link Entity}
     */
    E getEntity();
    
    /**
     * Método que obtém a entidade utilizada para pesquisa.
     *
     * @return {@link Entity}
     */
    E getSearch();
    
    /**
     * Método que define a entidade padrão do formulário.
     *
     * @param entity
     */
    void setEntity(E entity);
    
    /**
     * Método que define a entidade de pesquisa do formulário.
     *
     * @param search
     */
    void setSearch(E search);
    
    
    /**
     * Método que obtém uma {@link Collection} com as entidades.
     *
     * @return {@link Collection}
     */
    Collection<E> getCollectionEntities();
    
    /**
     * Método que limpa a entidade de inserção.
     *
     */
    void clearInsertData();

    
    /**
     * Método que limpa a entidade de pesquisa.
     *
     */
    void clearSearchData();

    
    /**
     * Método que limpar a coleção do formulário.
     *
     */
    void clearCollectionData();
}
