/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Usuario_Perfil;
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
public class Usuario_PerfilDAO  extends GenericHibernateDAO<Usuario_Perfil>{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        
        return this.sessionFactory;
    }
    
    public Collection<Usuario_Perfil> list (long idUsuario) {
        
        Criteria criteria = this.createCriteria();

        criteria.add(Restrictions.eq("id_usuario", idUsuario));

        return criteria.list();
        
    }
    
}
