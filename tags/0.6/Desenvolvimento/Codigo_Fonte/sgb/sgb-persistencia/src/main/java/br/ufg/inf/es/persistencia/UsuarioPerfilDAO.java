/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.Arrays;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Luã
 */
@Repository
@Transactional
public class UsuarioPerfilDAO  extends GenericHibernateDAO<UsuarioPerfil>{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }
    
    public Collection<UsuarioPerfil> list (long idUsuario) {
        
        Criteria criteria = this.createCriteria();

        criteria.add(Restrictions.eq("idUsuario", idUsuario));

        return criteria.list();
        
    }
    
    public Collection<Perfil> listPerfis(long idUsuario) {
        return Arrays.asList(new Perfil("ROLE_ADMIN"));
    }
    
}
