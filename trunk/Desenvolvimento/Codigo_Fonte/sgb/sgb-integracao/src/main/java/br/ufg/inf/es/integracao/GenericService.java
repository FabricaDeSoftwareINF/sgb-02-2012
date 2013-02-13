package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.service.Service;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.AbstractEntityModel;
import java.util.Collection;

/**
 * Classe genérica que define os métodos e atributos de um Service.
 * 
 * @author Cézar Augusto Ferreira
 */
public abstract class GenericService<E extends AbstractEntityModel> implements Service<E, Long> {

    /**
     * Método que obtém o DAO da entidade do Service.
     *
     * @return DAO
     */
    public abstract DAO<E, Long> getDAO();

    /** 
     * {@inheritDoc} 
     */
    public E find(Long id) {

        return this.getDAO().find(id);
    }

    /** 
     * {@inheritDoc} 
     */
    public Long insert(E entidade) throws ValidationException {

        return this.getDAO().insert(entidade);
    }

    /** 
     * {@inheritDoc} 
     */
    public void update(E entidade) throws ValidationException {

        this.getDAO().update(entidade);
    }

    /** 
     * {@inheritDoc} 
     */
    public void save(E entidade) throws ValidationException {

        this.getDAO().save(entidade);
    }

    /** 
     * {@inheritDoc} 
     */
    public void remove(E entidade) throws ValidationException {

        this.getDAO().remove(entidade);
    }

    /** 
     * {@inheritDoc} 
     */
    public void removeAll(Collection<E> entidades) throws ValidationException {

        this.getDAO().removeAll(entidades);
    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<E> search(E entidade) {

        return this.getDAO().search(entidade);
    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<E> list() {

        return this.getDAO().list();
    }

    /** 
     * {@inheritDoc} 
     */
    public void refresh(E entidade) {

        this.getDAO().refresh(entidade);
    }
    
    /**
     * Método que realiza as buscas pelos atributos.
     *
     * @param key
     * @param attributes
     * @return Collection
     */
    public Collection<E> searchByAttributes(String key, String... attributes) {
        
    	return this.getDAO().search(key, attributes);
    }
}