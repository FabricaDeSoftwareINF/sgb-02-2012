package br.ufg.inf.es.base.service;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.validation.ValidationException;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Cézar Augusto Ferreira
 */
public interface Service<E extends Entity<ID>, ID extends Serializable> extends Serializable {
 
    E find(final ID id);

    ID insert(final E entidade) throws ValidationException;

    void update(final E entidade) throws ValidationException;

    void save(final E entidade) throws ValidationException;

    void remove(final E entidade) throws ValidationException;

    void removeAll(final Collection<E> entidades) throws ValidationException;

    Collection<E> search(final E entidade);

    Collection<E> list();

    void refresh(final E entidade);
}