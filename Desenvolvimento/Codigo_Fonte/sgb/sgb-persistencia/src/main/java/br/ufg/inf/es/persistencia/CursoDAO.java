package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Curso;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe responsável por realizar as funções de persistência da entidade <code>Curso</code>
 *
 * @author Diogo Gon&ccedil;alves Teodoro * 
 */
@Repository
@Transactional
public class CursoDAO extends GenericHibernateDAO<Curso> {

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
