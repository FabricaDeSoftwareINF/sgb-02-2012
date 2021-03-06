package br.ufg.inf.es.persistencia;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Usuario;
import org.hibernate.FetchMode;
import org.hibernate.Query;

/**
 * Classe para o DAO da entidade ListaCompras
 *
 * @author Jackeline Neves
 */
@Repository
@Transactional
public class ListaComprasDAO extends GenericHibernateDAO<ListaCompras> {

    /**
     * Campo sessionFactory
     */
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

    /**
     * Método que retorna os livros de uma lista de compras.
     *
     * @param id
     * @return Collection<Livro>
     */
    public Collection<ItemListaCompras> findLivrosListaCompras(Long id) {
        Criteria criteria = this.getSession().createCriteria(ListaCompras.class);
        criteria.setFetchMode("livrosDaListaCompras", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        ListaCompras listaCompras = (ListaCompras) criteria.uniqueResult();
        return listaCompras.getLivrosDaListaCompras();
    }

    @Override
    public void save(ListaCompras listaCompras) {
        try {
            this.getSession().saveOrUpdate(listaCompras);
            this.getSession().flush();
        } finally {
            this.getSession().close();
        }
    }
    
    public Collection<ListaCompras> findListaByUser(Usuario user) {
        Query query = getSession().createQuery("FROM ListaCompras lc WHERE lc.user=:user");
        query.setParameter("user", user);
        return query.list();
    }
    
}
