package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ParametrosBiblioteca;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Victor Carvalho
 */
@Repository
@Transactional
public class ParametrosBibliotecaDAO extends GenericHibernateDAO<ParametrosBiblioteca> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }
}