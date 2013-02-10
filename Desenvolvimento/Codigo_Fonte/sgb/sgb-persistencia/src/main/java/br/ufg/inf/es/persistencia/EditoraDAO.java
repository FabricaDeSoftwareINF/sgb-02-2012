package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Editora;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Henrique Hirako, Cássio Augusto Silva de Freitas
 */
@Repository
@Transactional(rollbackFor=ValidationException.class)
public class EditoraDAO extends GenericHibernateDAO<Editora> {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }

}