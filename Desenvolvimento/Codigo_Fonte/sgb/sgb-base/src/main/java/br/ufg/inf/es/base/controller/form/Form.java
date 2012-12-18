package br.ufg.inf.es.base.controller.form;

import br.ufg.inf.es.base.model.Entity;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Cézar Augusto Ferreira
 */
public interface Form<E extends Entity> extends Serializable {
    
    E getEntity();
    
    E getSearch();
    
    void setEntity(E entity);
    
    void setSearch(E search);
    
    Collection<E> getCollectionEntities();
    
    void clearInsertData();
    
    void clearSearchData();
    
    void clearCollectionData();
}
