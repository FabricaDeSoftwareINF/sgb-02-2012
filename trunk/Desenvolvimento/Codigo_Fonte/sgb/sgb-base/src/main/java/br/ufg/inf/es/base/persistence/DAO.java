package br.ufg.inf.es.base.persistence;

import br.ufg.inf.es.base.model.Entity;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Cézar Augusto Ferreira
 */
public interface DAO<E extends Entity<ID>, ID extends Serializable> {

    E find(final ID id);

    ID insert(final E entidade);

    void update(final E entidade);

    void save(final E entidade);

    void remove(final E entidade);

    void removeAll(final Collection<E> entidades);

    Collection<E> search(final E entidade);

    Collection<E> list();

    void refresh(final E entidade);
}