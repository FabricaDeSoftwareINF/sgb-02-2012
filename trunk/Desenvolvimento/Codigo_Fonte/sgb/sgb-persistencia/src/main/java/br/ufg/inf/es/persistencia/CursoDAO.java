package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Curso;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Repository
@Transactional
public class CursoDAO extends GenericHibernateDAO<Curso> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }
}
