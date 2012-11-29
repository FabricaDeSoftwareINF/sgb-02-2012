package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Usuario;
import org.hibernate.SessionFactory;
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

    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

    
}