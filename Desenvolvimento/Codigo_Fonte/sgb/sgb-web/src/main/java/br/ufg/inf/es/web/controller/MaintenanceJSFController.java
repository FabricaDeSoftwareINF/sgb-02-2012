package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.controller.MaintenanceController;
import br.ufg.inf.es.base.controller.form.Form;
import br.ufg.inf.es.base.service.Service;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.AbstractEntityModel;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class MaintenanceJSFController<E extends AbstractEntityModel> 
        extends JSFController implements MaintenanceController {

    public abstract Form<E> getForm();
    
    public abstract Service<E, Long> getService();
    
    public String openInsertPage() {
        
        this.openInsertView();
        
        return this.getRootNavigation() + "insertPage";
    }
    
    @Override
    public void openInsertView() {
        
        this.getForm().clearInsertData();
    }

    @Override
    public void insert() {
        
        try {
            
            this.getService().insert(this.getForm().getEntity());
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public void openSearchView() {
        
        this.getForm().getCollectionEntities().addAll(this.getService().list());
    }

    public String openSearchPage() {
        
        this.openSearchView();
        
        return this.getRootNavigation() + "searchPage";
    }
    
    @Override
    public void search() {
        
        this.getForm().clearCollectionData();
        
        this.getForm().getCollectionEntities().addAll(this.getService().search(this.getForm().getSearch()));
    }

    @Override
    public void openEditView() {
        
        if (this.getForm().getEntity().isNew()) {
        
            try {
                
                Long entityID = Long.parseLong(this.getParameterFromRequest("entityID"));
                
                this.getForm().setEntity(this.getService().find(entityID));
            
            } catch (NumberFormatException e) {
                
                this.addErrorMessage("entityID parameter not found.");
            }
        }
    }
    
    public String openEditPage() {
        
        this.openEditView();
        
        return this.getRootNavigation() + "editPage";
    }

    @Override
    public void edit() {
        
        try {
            
            this.getService().update(this.getForm().getEntity());
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public void remove() {
        
        try {
            
            this.getService().remove(this.getForm().getEntity());
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }
    }   
}