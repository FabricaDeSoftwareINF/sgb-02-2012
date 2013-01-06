package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
    
    /**
     * @author Cássio Augusto Silva de Freitas
     * @param filtroNome
     * @return coleção de autores
     */
    public Collection<Autor> listarAutores(String filtroNome) {
        
        Transaction beginTransaction = this.getSessionFactory().getCurrentSession().beginTransaction();
        
        beginTransaction.begin();
       
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Autor.class);
        
        if(!(filtroNome == null || filtroNome.equals(""))) {
            
            criteria.add(Restrictions.ilike("nome", filtroNome, MatchMode.ANYWHERE));
       
        }
        
        return criteria.list();
    }
    
}