package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Henrique Hirako
 */
@Repository
@Transactional
public class AutorDAO extends GenericHibernateDAO<Autor> {
 
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }
}