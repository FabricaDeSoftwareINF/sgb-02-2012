package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.controller.Controller;
import br.ufg.inf.es.model.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Cézar Augusto Ferreira
 */

public abstract class JSFController extends BaseController implements Controller {
    
    
    @Override
    public void initData() {
        
    }
    
    @Override
    public void openInitialView() {
        
        this.initData();
    }
    
    public String openInitialPage() {
        
        this.openInitialView();
        
        return this.getOpenInitialPageNavigator();
    }

    private String getOpenInitialPageNavigator() {
        
        return this.getRootNavigation() + "initialPage";
    }

    protected String getRootNavigation() {
        
        return this.getClass().getSimpleName() + "/";
    }   
    
}