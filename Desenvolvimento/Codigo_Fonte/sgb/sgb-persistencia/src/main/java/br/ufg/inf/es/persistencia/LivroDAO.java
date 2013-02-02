/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cezar
 */
@Repository
@Transactional
public class LivroDAO extends GenericHibernateDAO<Livro> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;

    }

    public Collection<Autor> getAutores(Long id) {

        return this.getCollection(id, "autores");
    }
    
    public Collection<Bibliografia> getBibliografia(Long id) {
      
        return this.getCollection(id, "bibliografias");
  
    }
}
