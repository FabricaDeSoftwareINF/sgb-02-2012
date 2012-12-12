package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001;
import br.ufg.inf.es.integracao.annotations.RNG002;
import br.ufg.inf.es.integracao.annotations.RNG003;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.PerfilDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    private UsuarioDAO dao;

    @Autowired
    private PerfilDAO perfilDao;
    
    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    public void setDao(UsuarioDAO dao) {

        this.dao = dao;
    }

    public PerfilDAO getPerfilDao() {
        return perfilDao;
    }

    public void setPerfilDao(PerfilDAO perfilDao) {
        this.perfilDao = perfilDao;
    }
    
    @Override
    @RNG001
    @RNG002
    @RNG003
    public Long insert(Usuario entity) throws ValidationException {
        
        entity.setDataCadastro(new Date());
        
        entity.setStatus(true);
        
        Long id = super.insert(entity);
     
        for (Perfil perfil : entity.getPerfil()) {
            
            this.getPerfilDao().vincularUsuarioPerfil(entity, perfil);
        }
        
        return id;
    }
    
    @Override
    @RNG001
    @RNG002
    @RNG003
    public void update(Usuario entity) throws ValidationException {
        entity.setStatus(true);
        
        super.update(entity);
     
        for (Perfil perfil : entity.getPerfil()) {
            
            this.getPerfilDao().vincularUsuarioPerfil(entity, perfil);
        }
    }
}