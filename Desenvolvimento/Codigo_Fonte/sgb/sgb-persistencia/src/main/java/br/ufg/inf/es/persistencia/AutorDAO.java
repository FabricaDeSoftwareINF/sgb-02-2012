package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Henrique Hirako, Cássio Augusto Silva de Freitas
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
     * Método responsável por obter uma coleção de Autores em DTO e listar todos
     * eles
     *
     * @author Cássio Augusto Silva de Freitas
     * @param filtroNome
     * @return coleção de autores
     */
    public Collection<AutorDTO> listarAutores(String filtroNome) {

        Criteria criteria = this.getSession().createCriteria(Autor.class,"autor");

        if (!(filtroNome == null || filtroNome.equals(""))) {

            criteria.add(Restrictions.ilike("autor.nome", filtroNome, MatchMode.ANYWHERE));

        }
        
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        criteria.setProjection(Projections.projectionList().add(Projections.property("autor.id"), "id").add(Projections.property("autor.nome"), "nome").add(Projections.property("autor.sobrenome"), "sobrenome")).setResultTransformer(Transformers.aliasToBean(AutorDTO.class));

        criteria.addOrder(Order.asc("nome"));

        return criteria.list();
    }
}