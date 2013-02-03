/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jackeline Neves
 */
@Repository
@Transactional
public class ListaComprasDAO extends GenericHibernateDAO<ListaCompras> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;

    }

    
    public Collection<Livro> getLivros(Long id) {
        
        Criteria criteria = this.getSession().createCriteria(Livro.class);
        
        criteria.createAlias("listaCompras", "lc");
    
        criteria.add(Restrictions.eq("lc.id", id));
        
        return criteria.list();
    }
}
