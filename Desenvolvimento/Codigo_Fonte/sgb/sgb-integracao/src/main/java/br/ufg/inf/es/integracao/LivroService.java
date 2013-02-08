package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG002Livro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
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
    @RNG002Livro
    public Long insert(Livro entity) throws ValidationException {
        Long id = 0l;
        try {
            id = super.insert(entity);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            String message = messageToProperty(e.getCause().getMessage());
            throw new ValidationException(message);
        }
        return id;
    }

    protected String messageToProperty(String message) {
        String[] msg = message.split(" ");
        String prefix = msg[0].toLowerCase();
        String sufix = msg[msg.length - 1].replace("'", "").toLowerCase();
        return prefix.concat(".").concat(sufix);
    }

    @RNG002Livro
    public void update(Livro entity) throws ValidationException {
        try {
            super.update(entity);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            String message = messageToProperty(e.getCause().getMessage());
            throw new ValidationException(message);
        }
    }

}