package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Autor.
 *
 * @author Henrique Hirako, Cássio Augusto Silva de Freitas
 */
@Repository
@Transactional(rollbackFor = ValidationException.class)
public class AutorDAO extends GenericHibernateDAO<Autor> {

    /**
     * Campo sessionFactory
     */
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
     * Método responsável por obter uma coleção de Autores em DTO e listar todos
     * eles.
     *
     * @author Cássio Augusto Silva de Freitas
     * @param filtroNome
     * @return coleção de autores
     */
    public Collection<AutorDTO> listarAutores(String filtroNome) {
        Criteria criteria = this.getSession().createCriteria(Autor.class, "autor");

        if (!(filtroNome == null || filtroNome.equals(""))) {
            criteria.add(Restrictions.ilike("autor.nome", filtroNome, MatchMode.ANYWHERE));
        }
        criteria.setProjection(
                Projections.projectionList().
                add(Projections.property("autor.id"), "id").
                add(Projections.property("autor.nome"), "nome").
                add(Projections.property("autor.sobrenome"), "sobrenome"));
        
        criteria.setResultTransformer(new AliasToBeanResultTransformer(AutorDTO.class));
        criteria.addOrder(Order.asc("nome"));

        return criteria.list();
    }
}
