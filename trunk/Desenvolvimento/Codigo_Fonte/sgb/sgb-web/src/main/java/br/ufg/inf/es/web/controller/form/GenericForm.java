package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.base.controller.form.Form;
import br.ufg.inf.es.model.AbstractEntityModel;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author Cézar Augusto Ferreira
 */
public class GenericForm<E extends AbstractEntityModel> implements Form<E> {

    private E entity;
    private E search;
    private Collection<E> collectionEntities;

    public GenericForm() {
        try {

            this.entity = this.getClassEntity().newInstance();

            this.search = this.getClassEntity().newInstance();

            this.collectionEntities = new ArrayList<E>();

        } catch (Exception ex) {

            Logger.getLogger(GenericForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected final Class<E> getClassEntity() {

        final Type type[] = ((ParameterizedTypeImpl) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments();

        return (Class<E>) type[0];
    }

    @Override
    public E getEntity() {

        return this.entity;
    }

    @Override
    public E getSearch() {

        return this.search;
    }

    @Override
    public Collection<E> getCollectionEntities() {

        return this.collectionEntities;
    }

    public void setCollectionEntities(Collection<E> collectionEntities) {

        this.collectionEntities = collectionEntities;
    }

    @Override
    public void clearInsertData() {
        
        this.entity = this.createNewInstance();
    }

    private E createNewInstance() {

        try {

            return this.getClassEntity().newInstance();

        } catch (Exception ex) {

            Logger.getLogger(GenericForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void clearSearchData() {
        
        this.search = this.createNewInstance();
    }

    @Override
    public void clearCollectionData() {
        
        this.collectionEntities.clear();
    }

    @Override
    public void setEntity(E entity) {
        
        this.entity = entity;
    }

    @Override
    public void setSearch(E search) {
        
        this.search = search;
    }
}
