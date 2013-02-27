package br.ufg.inf.es.web.bootstrap;

import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.model.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
    
    private static final BigDecimal VALOR_FRETE = BigDecimal.valueOf(30);
    
    private static final int PARAMETRO_MEC = 5; 

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        
        sessionFactory = applicationContext.getBean(SessionFactory.class);

        if (sessionFactory == null) {
            Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, "sessionFactory is null");
        } else {
            initializeDefaulUser();
            crieBibliografias();
            initializeDefaulComunication();
            inicializaParametrosDefault();
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

                Usuario user = new Usuario();
                user.setNome("Administrador");
                user.setSobrenome("do Sistema");
                user.setEmail("admin@email.com");
                user.setSenha(new SgbCryptography().encrypt("123456"));
                user.setStatus(true);
                user.setPerfil(UsuarioPerfil.ADM);
                user.setDataCadastro(Calendar.getInstance().getTime());

                Long idUser = insert(sessionFactory, user);
                user.setId(idUser);
            } catch (Exception ex) {
                Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void inicializaParametrosDefault(){
        
        Collection parametros = list(this.getSessionFactory(), Parametros.class);
        
        if(parametros == null || parametros.isEmpty()){
            
            Parametros param = new Parametros();
            
            param.setParametroMEC(Bootstrap.PARAMETRO_MEC);
            
            param.setValorFrete(Bootstrap.VALOR_FRETE);
            
            this.insert(this.getSessionFactory(), param);
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
    
     private void initializeDefaulComunication() {
        Collection comunicacaoList = list(sessionFactory, Comunicacao.class);
        if (comunicacaoList == null || comunicacaoList.isEmpty()) {
            try {

                Comunicacao comunicacao = new Comunicacao();
                comunicacao.setService("smtp.gmail.com");
                comunicacao.setSsl(true);
                comunicacao.setTsl(true);
                comunicacao.setPort("465");
                comunicacao.setUsuario("sapcl.ufg");
                comunicacao.setSenha(new CriptoGeneric().criptografa("sapcl321"));
                
                insert(sessionFactory, comunicacao);
            } catch (Exception ex) {
                Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
