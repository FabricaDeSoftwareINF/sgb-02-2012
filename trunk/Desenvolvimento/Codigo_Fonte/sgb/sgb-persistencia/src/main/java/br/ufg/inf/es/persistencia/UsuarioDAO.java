package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para o DAO da entidade Usuario
 * @author Cézar Augusto Ferreira
 */
@Repository
@Transactional
public class UsuarioDAO extends GenericHibernateDAO<Usuario> {
 
    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Construtor desta classe.
     * @param sessionFactory
     */
    public UsuarioDAO(SessionFactory sessionFactory) {
    	
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Construtor desta classe.
     */
    public UsuarioDAO() {
        
    }

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
     * Método que obtém um usuário pelo email.
     *
     * @param user
     * @return Usuario
     */
    public Usuario findUserByEmail(String user) {
    	
        Criteria criteria = this.createCriteria();
        
        criteria.add(Restrictions.eq("email", user));
        
        return (Usuario) criteria.uniqueResult();
    }

    /**
     * Método que obtém um usuário pelo email e pela senha.
     *
     * @param user
     * @param password
     * @return Usuario
     */
    public Usuario findUserByEmailAndPassword(String user, String password) {
    	
        Criteria criteria = this.createCriteria();
        
        criteria.add(Restrictions.eq("email", user));
        
        criteria.add(Restrictions.eq("senha", password));
        
        return (Usuario) criteria.uniqueResult();
    }
}