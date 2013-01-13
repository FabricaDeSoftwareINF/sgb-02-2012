/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Diogo Ribeiro
 */
public class Bootstrap implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());

        sessionFactory = applicationContext.getBean(SessionFactory.class);

        initializeDefaulUser();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void initializeDefaulUser() {
        if (sessionFactory == null) {
            
            Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, "sessionFactory is null");

            return;
        }

        Collection usuarios = list(sessionFactory, Usuario.class);
        if (usuarios == null || usuarios.isEmpty()) {
            try {
                
                Perfil perfil = new Perfil("ROLE_ADMIN");
                Long idPerfil = insert(sessionFactory, perfil);
                perfil.setId(idPerfil);
                
                if (idPerfil == null) {
                    
                    return;
                }
                
                Usuario user = new Usuario();
                user.setEmail("admin@email.com");
                user.setSenha(new SgbCryptography().encrypt("123456"));
                user.setStatus(true);
                user.setPerfil(Arrays.asList(perfil));
                
                Long idUser = insert(sessionFactory, user);
                user.setId(idUser);
                
                UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                usuarioPerfil.setIdPerfil(idPerfil);
                usuarioPerfil.setIdUsuario(idUser);
                insert(sessionFactory, usuarioPerfil);
            } catch (Exception ex) {
                Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Collection list(SessionFactory sessionFactory, Class clazz) {
        Criteria criteria = sessionFactory.openSession().createCriteria(clazz);
        return criteria.list();
    }

    public Long insert(SessionFactory sessionFactory, Object entidade) {
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(entidade);
        session.flush();
        session.close();
        
        return id;
    }
    
    public void update(SessionFactory sessionFactory, Object entidade){
        Session session = sessionFactory.openSession();
        session.merge(entidade);
        session.flush();
        session.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
