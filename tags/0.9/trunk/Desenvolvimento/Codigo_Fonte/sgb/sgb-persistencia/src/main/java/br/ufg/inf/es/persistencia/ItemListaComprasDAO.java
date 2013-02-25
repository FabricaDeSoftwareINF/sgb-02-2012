package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ItemListaCompras;
import java.util.Collection;
import org.hibernate.Query;
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
public class ItemListaComprasDAO extends GenericHibernateDAO<ItemListaCompras> {

    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Construtor desta classe.
     * @param sessionFactory
     */
    public ItemListaComprasDAO(SessionFactory sessionFactory) {
        
    	this.sessionFactory = sessionFactory;
    }
    
    /**
     * Construtor desta classe.
     */
    public ItemListaComprasDAO() {
        
    }

    /** 
     * {@inheritDoc} 
     */
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

    @Override
    public Collection<ItemListaCompras> list() {
        Query query = getSession().createQuery("FROM LivroListaCotacao llc");
        return query.list();
    }
    
}
