package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.CotacoesLivro;
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
public class CotacoesLivroDAO extends GenericHibernateDAO<CotacoesLivro> {
 
    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Construtor desta classe.
     * @param sessionFactory
     */
    public CotacoesLivroDAO(SessionFactory sessionFactory) {
        
    	this.sessionFactory = sessionFactory;
    }
    
    /**
     * Construtor desta classe.
     */
    public CotacoesLivroDAO() {
        
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