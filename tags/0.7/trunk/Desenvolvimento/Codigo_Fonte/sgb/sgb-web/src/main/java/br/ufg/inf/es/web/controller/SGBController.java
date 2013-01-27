package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.GenericService;
import br.ufg.inf.es.model.AbstractEntityModel;
import br.ufg.inf.es.web.controller.form.GenericForm;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class SGBController<E extends AbstractEntityModel, 
        F extends GenericForm<E>, 
        S extends GenericService<E>> extends MaintenanceJSFController<E> {

    @Override
    public abstract F getForm();

    @Override
    public abstract S getService();

    @Override
    public void initData() {
        
        this.getForm().clearInsertData();
        
        this.getForm().clearSearchData();
        
        this.getForm().setCollectionEntities(this.getService().list());
    }
}