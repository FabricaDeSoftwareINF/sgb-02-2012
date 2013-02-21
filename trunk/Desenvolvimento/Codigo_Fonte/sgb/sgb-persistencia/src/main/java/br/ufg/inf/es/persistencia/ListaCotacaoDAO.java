package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ListaCotacao;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade ListaCotacao
 *
 * @author Bruno Marquete
 */
@Repository
@Transactional
public class ListaCotacaoDAO extends GenericHibernateDAO<ListaCotacao> {

    /**
     * Campo sessionFactory
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Construtor desta classe.
     *
     * @param sessionFactory
     */
    public ListaCotacaoDAO(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * Construtor desta classe.
     */
    public ListaCotacaoDAO() {
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
    public Collection<ListaCotacao> list() {
        Query query = getSession().createQuery("FROM ListaCotacao lc");
        return query.list();
    }
}