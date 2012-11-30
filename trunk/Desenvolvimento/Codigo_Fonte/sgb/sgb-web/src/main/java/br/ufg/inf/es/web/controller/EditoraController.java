package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.web.controller.form.EditoraForm;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class EditoraController
        extends SGBController<Editora, EditoraForm, EditoraService> {

    @Autowired
    private EditoraForm form;
    
    @Autowired
    private EditoraService service;
    
    @Override
    public EditoraForm getForm() {

        return this.form;
    }

    @Override
    public EditoraService getService() {

        return this.service;
    }

    public void setForm(EditoraForm form) {
        
        this.form = form;
    }

    public void setService(EditoraService service) {
        
        this.service = service;
    }
    
    
}