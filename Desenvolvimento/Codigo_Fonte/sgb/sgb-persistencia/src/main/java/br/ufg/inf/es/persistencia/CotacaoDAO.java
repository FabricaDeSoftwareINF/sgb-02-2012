package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Cotacao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Marquete
 */
@Repository
@Transactional
public class CotacaoDAO extends GenericHibernateDAO<Cotacao> {
 
    @Autowired
    private SessionFactory sessionFactory;

    public CotacaoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     public CotacaoDAO() {
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

     
    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

}