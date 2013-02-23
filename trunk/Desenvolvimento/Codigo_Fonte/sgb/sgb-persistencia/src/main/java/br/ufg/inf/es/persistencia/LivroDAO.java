package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    public Collection<Autor> getAutores(Long id) {
        Criteria criteria = getSession().createCriteria(Livro.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("autores", FetchMode.JOIN);
        Livro livro = (Livro) criteria.uniqueResult();
        return livro.getAutores();
    }

    
    /**
     * Método que obtém as bibliografias de um livro
     *
     * @param id
     * @return Collection<Bibliografia>
     */
    public Collection<?> getBibliografia(Long id) {

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
     * Método que obtém a quantidade de alunos de acordo com as disciplinas 
     * que utilizam o determinado livro.
     * 
     * @param id Identificador do livro.
     * @return Quantidade de alunos 
     */
    public Integer obterQuantidadeDeAlunosPorLivro(Long id) {
        
        Criteria criteria = this.createCriteria();
        
        criteria.createAlias("bibliografias", "biblio");
        
        criteria.add(Restrictions.eq("biblio.livro.id", id));
        
        criteria.createAlias("biblio.disciplina", "discip");
        
        criteria.createAlias("discip.curso", "curs");
        
        criteria.setProjection(Projections.projectionList().add(Projections.sum("curs.vagas")));
        
        return UtilObjeto.isReferencia(criteria.uniqueResult()) ? (Integer) criteria.uniqueResult() : 0;
    }
    
    @Override
    public Collection<Livro> list() {
        Query query = getSession().createQuery("FROM Livro l");
        return query.list();
    }
    
    public void removeLivros(Collection<Livro> livros) throws ValidationException {
        List<Livro> livrosNaoRemovidos = new ArrayList<Livro>();
        for (Livro livro : livros) {
            Criteria criteriaCompra = this.getSession().createCriteria(ItemListaCompras.class);
            criteriaCompra.add(Restrictions.eq("livro", livro));
            Collection<ItemListaCompras> itensCompra = criteriaCompra.list();
            Criteria criteriaCotacao = this.getSession().createCriteria(ItemListaCotacao.class);
            criteriaCotacao.add(Restrictions.eq("livro", livro));
            Collection<ItemListaCotacao> itensCotacao = criteriaCotacao.list();
            if ((itensCompra == null ||  itensCompra.isEmpty())
                    && (itensCotacao == null || itensCotacao.isEmpty())) {
                this.remove(livro);
            } else {
                livrosNaoRemovidos.add(livro);
            }
        }
        if (!livrosNaoRemovidos.isEmpty()) {
            throw new ValidationException("cadastro.livro.remocao.dependencia");
        }
    }
    
}
