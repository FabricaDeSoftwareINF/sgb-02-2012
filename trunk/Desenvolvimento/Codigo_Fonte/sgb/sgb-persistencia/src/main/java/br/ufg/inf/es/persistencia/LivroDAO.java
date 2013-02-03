package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
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

    /**
     * Método responsável por buscar um Livro de acordo com seu título.
     *
     * @author Cássio Augusto Silva de Freitas
     * @param query
     */
    public Collection<Livro> buscaLivroPorTitulo(String query) {

        Criteria criteria = this.createCriteria();
        
        if (UtilObjeto.isReferencia(query) && !query.equals("")) {
        
            criteria.add(Restrictions.ilike("titulo", query, MatchMode.ANYWHERE));
        
        }
        
        return criteria.list();
    }
    
    /**
     * 
     * @param filtroTitulo
     * @return
     * @author Jackeline
     */
    public Collection<Livro> listarLivros(String filtroTitulo) {

        Criteria criteria = this.getSession().createCriteria(Livro.class, "livro");

        if (!(filtroTitulo == null || filtroTitulo.equals(""))) {

            criteria.add(Restrictions.ilike("livro.titulo", filtroTitulo, MatchMode.ANYWHERE));

        }

        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        criteria.setProjection(Projections.projectionList().add(Projections.property("livro.id"), "id").add(Projections.property("livro.titulo"), "titulo")).setResultTransformer(Transformers.aliasToBean(Livro.class));

        criteria.addOrder(Order.asc("titulo"));

        return criteria.list();
    }
    
}
