package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.model.*;
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
 * Insere alguns dados default para a aplicação, como usuarios e perfis por exemplo.
 *
 * @author Diogo Ribeiro, Victor Carvalho
 */
public class Bootstrap implements ServletContextListener {

    private SessionFactory sessionFactory;
    private ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        sessionFactory = applicationContext.getBean(SessionFactory.class);

        if (sessionFactory == null) {
            Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, "sessionFactory is null");
        } else {
            initializeDefaulUser();
            crieBibliografias();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void crieBibliografias() {
        Collection livros = list(sessionFactory, Livro.class);
        if (livros == null || livros.isEmpty()) {
            try {
                BibliografiaBootstrap.crieBibliografias(sessionFactory);
            } catch (Exception ex) {
                Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, "Erro ao criar bibliografias", ex);
            }
        }
    }

    private void initializeDefaulUser() {
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

    private Long insert(SessionFactory sessionFactory, Object entidade) {
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(entidade);
        session.flush();
        session.close();

        return id;
    }

    private void update(SessionFactory sessionFactory, Object entidade) {
        Session session = sessionFactory.openSession();
        session.merge(entidade);
        session.flush();
        session.close();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
