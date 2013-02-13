package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    /**
     * Método que lista todas as bibliografias de acordo com um Livro.
     *
     * @param livro
     * @return Lista com as bibliografias
     */
    public List<Bibliografia> findAllByLivro(Livro livro) {
        Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.eq("livro", livro));

        return (List<Bibliografia>) criteria.list();
    }
}
