package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cezar
 */
@Repository
@Transactional
public class LivroDAO extends GenericHibernateDAO<Livro> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Collection<?> getAutores(Long id) {

        return this.getCollection(id, "autores");
    }
    
    public Collection<?> getBibliografia(Long id) {
        
       //Criteria criteria = this.getSession().createCriteria(Bibliografia.class);
       
       //criteria.add(Restrictions.eq("livro.id", id));
               
       return this.getCollection(id, "bibliografias"); 
    }
}
