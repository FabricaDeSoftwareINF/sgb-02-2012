package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ItemListaCotacao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinicius
 */
@Repository
@Transactional
public class ItemListaCotacaoDAO extends GenericHibernateDAO<ItemListaCotacao> {
 
    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Construtor desta classe.
     * @param sessionFactory
     */
    public ItemListaCotacaoDAO(SessionFactory sessionFactory) {
        
    	this.sessionFactory = sessionFactory;
    }
    
    /**
     * Construtor desta classe.
     */
    public ItemListaCotacaoDAO() {
        
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        
    	this.sessionFactory = sessionFactory;
    }
     
    /** 
     * {@inheritDoc} 
     */
    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

}