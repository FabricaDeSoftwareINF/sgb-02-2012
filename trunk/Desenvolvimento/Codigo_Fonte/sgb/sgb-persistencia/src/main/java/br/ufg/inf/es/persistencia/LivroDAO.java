package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Livro
 * @author cezar
 */
@Repository
@Transactional
public class LivroDAO extends GenericHibernateDAO<Livro> {

    /** Campo sessionFactory*/
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
     * Método que  obtem os autores da entidade.
     *
     * @param id
     * @return Colecao de Atoures
     */
    public Collection<?> getAutores(Long id) {

        return this.getCollection(id, "autores");
    }

    
    /**
     * Método que obtém as bibliografias de um livro
     *
     * @param id
     * @return Collection<Bibliografia>
     */
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
     * {@inheritDoc} 
     */
    @Override
    public void update(Livro livro) {
        try {
            isReferencia(livro);

            if (livro.getBibliografias() != null) {
                for (Bibliografia bibliografia : livro.getBibliografias()) {
                    if (bibliografia.isNew()) {
                        Disciplina disciplina = bibliografia.getDisciplina();
                        Collection<Bibliografia> bibliografias = disciplina.getBibliografias();
                        if (bibliografias != null) {
                            bibliografias.add(bibliografia);
                        }
                        //this.getSession().merge(disciplina);
                        //this.getSession().persist(bibliografia);
                    }
                }
            }

            this.getSession().merge(livro);

            this.getSession().flush();

        } finally {

            this.getSession().close();
        }
    }
}
