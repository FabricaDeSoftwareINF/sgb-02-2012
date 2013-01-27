package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cézar Augusto Ferreira
 */
@Repository
@Transactional
public class UsuarioDAO extends GenericHibernateDAO<Usuario> {
 
    @Autowired
    private SessionFactory sessionFactory;

    public UsuarioDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     public UsuarioDAO() {
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

     
    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

    public Usuario findUserByEmail(String user) {
        Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.eq("email", user));
        
        return (Usuario) criteria.uniqueResult();
    }

    public Usuario findUserByEmailAndPassword(String user, String password) {
        Criteria criteria = this.createCriteria();
        criteria.add(Restrictions.eq("email", user));
        criteria.add(Restrictions.eq("senha", password));
        
        return (Usuario) criteria.uniqueResult();
    }
}