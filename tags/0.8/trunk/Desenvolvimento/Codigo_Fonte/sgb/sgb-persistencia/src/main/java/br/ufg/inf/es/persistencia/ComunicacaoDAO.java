package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Comunicacao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Comunicacao
 * @author igor
 */
@Repository
@Transactional(rollbackFor=ValidationException.class)
public class ComunicacaoDAO extends GenericHibernateDAO<Comunicacao>{

    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /** 
     * {@inheritDoc} 
     */
    @Override
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
     * Método que retorna a <code>Comunicacao</code>
     *
     * @return Comunicacao
     */
    public Comunicacao getComunicacao(){
    	
        Criteria criteria = this.getSession().createCriteria(Comunicacao.class);

        return (Comunicacao) criteria.uniqueResult();
    }
    
}
