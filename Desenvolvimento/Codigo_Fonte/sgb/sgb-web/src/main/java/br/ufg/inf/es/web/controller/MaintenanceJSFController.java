package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.controller.MaintenanceController;
import br.ufg.inf.es.base.controller.form.Form;
import br.ufg.inf.es.base.service.Service;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.AbstractEntityModel;
import org.apache.commons.collections.Factory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

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
            
            this.addSuccessMessage("arquitetura.msg.sucesso");
            
            this.initData();
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public void openSearchView() {
        
        this.getForm().clearCollectionData();
        
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
    
    private void loadEntityFromRequest() {
        
        Long entityID = Long.parseLong(this.getParameterFromRequest("entityID"));
                
        this.getForm().setEntity(this.getService().find(entityID));
    }
    
    public String openEditPage() {
        try {
            this.openEditView();

            return this.getRootNavigation() + "editPage";
        } catch (Exception e) {
        }
        return "";
    }
    
    public String openEditPage(E entity) {
        
        this.getForm().clearInsertData();
        
        this.getForm().setEntity(entity);
                
        return this.getRootNavigation() + "editPage";
    }

    @Override
    public void openEditView() {
        
        if (this.getForm().getEntity().isNew()) {
        
            try {
                
                this.loadEntityFromRequest();
            
            } catch (NumberFormatException e) {
                
                this.addErrorMessage("entityID parameter not found.");
            }
        }
    }
    
    public String openViewPage() {
        try {
            
            return this.getRootNavigation() + "viewPage";
            
        } catch (Exception e) {            
        }
        return "";
    }
    
    public String openViewPage(E entity) {
        
        this.getForm().setEntity(entity);
        
        return this.getRootNavigation() + "viewPage";
    }

    @Override
    public void edit() {
        
        try {
            Hibernate.initialize(this.getForm().getEntity());
            
            this.getService().update(this.getForm().getEntity());
            
            this.initData();
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }        
    }

    @Override
    public void remove() {
        
        if (this.getForm().getEntity().isNew()) {
            
            this.loadEntityFromRequest();
        }
        
        try {
            
            this.getService().remove(this.getForm().getEntity());
            
            this.initData();
            
        } catch (ValidationException ex) {
            
            this.addWarningMessage(ex.getKeyMessage());
        }
    }   
}