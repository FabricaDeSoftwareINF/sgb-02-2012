package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.RNG001;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    private UsuarioDAO dao;

    @Override
    public UsuarioDAO getDAO() {

        return this.dao;
    }

    @Override
    @Validator(validatorClass = RNG001.class)
    public Long insert(Usuario entity) throws ValidationException {

        return super.insert(entity);
    }
}