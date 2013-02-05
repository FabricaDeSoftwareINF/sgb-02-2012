package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ListaCotacao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Marquete
 */
@Repository
@Transactional
public class ListaCotacaoDAO extends GenericHibernateDAO<ListaCotacao> {
 
    @Autowired
    private SessionFactory sessionFactory;

    public ListaCotacaoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     public ListaCotacaoDAO() {
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

     
    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

}