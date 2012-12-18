package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.service.Service;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.AbstractEntityModel;
import java.util.Collection;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class GenericService<E extends AbstractEntityModel> implements Service<E, Long> {

    public abstract DAO<E, Long> getDAO();

    public E find(Long id) {

        return this.getDAO().find(id);
    }

    public Long insert(E entidade) throws ValidationException {

        return this.getDAO().insert(entidade);
    }

    public void update(E entidade) throws ValidationException {

        this.getDAO().update(entidade);
    }

    public void save(E entidade) throws ValidationException {

        this.getDAO().save(entidade);
    }

    public void remove(E entidade) throws ValidationException {

        this.getDAO().remove(entidade);
    }

    public void removeAll(Collection<E> entidades) throws ValidationException {

        this.getDAO().removeAll(entidades);
    }

    public Collection<E> search(E entidade) {

        return this.getDAO().search(entidade);
    }

    public Collection<E> list() {

        return this.getDAO().list();
    }

    public void refresh(E entidade) {

        this.getDAO().refresh(entidade);
    }
}