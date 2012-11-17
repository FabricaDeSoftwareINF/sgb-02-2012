package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.AbstractEntityModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class SGBDAO<E extends AbstractEntityModel> extends GenericHibernateDAO<E> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return this.entityManager;
    }   
}