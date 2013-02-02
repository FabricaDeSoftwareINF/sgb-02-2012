/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Disciplina;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cezar
 */
@Repository
@Transactional(rollbackFor=ValidationException.class)
public class DisciplinaDAO extends GenericHibernateDAO<Disciplina> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }
    
    /**
     * Método responsável por listar as disciplinas cadastradas que ainda não foram
     * vinculadas a nenhum curso.
     * @return Coleção com a disciplinas.
     */
    public Collection<Disciplina> listarDisciplinasNaoVinculadasACurso(){
        
        Criteria criteria = this.createCriteria();
        
        criteria.add(Restrictions.isNull("curso.id"));
        
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        
        criteria.addOrder(Order.asc("nome"));
        
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("nome"), "nome"))
                .setResultTransformer(new AliasToBeanResultTransformer(Disciplina.class)); 
                
        return criteria.list();
    }

    /**
     * Método responsável por realizar a busca pelos atributos preenchidos.
     *
     * @author Allan Vieira Ribeiro
     * 
     * @param entidade Entidade utilizada para a busca.
     * 
     * @return  As entidades encontradas.
     */
    @Override
    public Collection<Disciplina> search(Disciplina entidade) {
        
        Criteria criteria = this.createCriteria();
        
        if(UtilObjeto.isReferencia(entidade.getId())){
            
            criteria.add(Restrictions.eq("id", entidade.getId()));
        }
        
        if(UtilObjeto.isReferencia(entidade.getCodigo()) && !entidade.getCodigo().isEmpty()){
            
            criteria.add(Restrictions.eq("codigo", entidade.getCodigo()));
        }
        
        if(UtilObjeto.isReferencia(entidade.getNome()) && !entidade.getNome().isEmpty()){
            
            criteria.add(Restrictions.eq("nome", entidade.getNome()));
        }       
        
        return criteria.list();
    }   
    
    /**
     * Método responsável por obter a lista de disciplinas de um determinado curso
     * @param id Identificador do curso
     * @return Lista de disciplinas
     * 
     */
     public Collection<Disciplina> listarDisciplinasDeUmCurso(Long id) {
      
         Criteria criteria = this.createCriteria();
         
         criteria.add(Restrictions.eq("curso.id", id));
         
         return criteria.list();
     }
}
