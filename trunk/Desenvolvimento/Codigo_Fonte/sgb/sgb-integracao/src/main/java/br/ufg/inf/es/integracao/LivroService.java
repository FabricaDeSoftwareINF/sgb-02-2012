package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG000;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LivroService extends GenericService<Livro> {

    @Autowired
    private LivroDAO dao;

    @Override
    public LivroDAO getDAO() {
        return this.dao;
    }

    public void setDao(LivroDAO dao) {

        this.dao = dao;
    }

    @Override
    @RNG000
    public Long insert(Livro entity) throws ValidationException {

        return super.insert(entity);
    }

}