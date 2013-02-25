package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Bibliografia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Bibliografia.
 * @author victor
 */
@Repository
@Transactional
public class BibliografiaDAO extends GenericHibernateDAO<Bibliografia> {

    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /** 
     * {@inheritDoc} 
     */
    @Override
    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}