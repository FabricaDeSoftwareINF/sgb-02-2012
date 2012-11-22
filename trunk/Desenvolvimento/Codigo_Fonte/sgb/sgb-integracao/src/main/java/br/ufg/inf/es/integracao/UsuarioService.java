package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.UsuarioDAO;
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

    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    public void setDao(UsuarioDAO dao) {

        this.dao = dao;
    }

    @Override
    @RNG001
    public Long insert(Usuario entity) throws ValidationException {

        return super.insert(entity);
    }
}