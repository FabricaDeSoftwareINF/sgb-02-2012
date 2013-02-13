package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Parametros;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade 
 * @author Victor Carvalho
 */
@Repository
@Transactional
public class ParametrosDAO extends GenericHibernateDAO<Parametros> {

    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

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
}