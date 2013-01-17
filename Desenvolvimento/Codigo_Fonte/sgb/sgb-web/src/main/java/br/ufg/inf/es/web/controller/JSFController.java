package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.controller.Controller;

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
        
        return this.getRootNavigation() + "OP";
    }

    protected String getRootNavigation() {
        
        return this.getClass().getSimpleName() + "/";
    }   
}