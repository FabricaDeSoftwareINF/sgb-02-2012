/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Luã
 */
@Repository
@Transactional()
public class PerfilDAO extends GenericHibernateDAO<Perfil> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }
}