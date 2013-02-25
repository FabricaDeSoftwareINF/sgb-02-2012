package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Cotacao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Cotacao
 *
 * @author Bruno Marquete
 */
@Repository
@Transactional
public class CotacaoDAO extends GenericHibernateDAO<Cotacao> {

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
    public CotacaoDAO(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * Construtor desta classe.
     */
    public CotacaoDAO() {
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