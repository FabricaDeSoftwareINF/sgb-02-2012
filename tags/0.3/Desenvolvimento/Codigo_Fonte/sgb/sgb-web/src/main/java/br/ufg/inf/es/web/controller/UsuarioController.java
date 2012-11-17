package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.UsuarioService;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.web.controller.form.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class UsuarioController 
    extends SGBController<Usuario, UsuarioForm, UsuarioService> {

    @Autowired
    private UsuarioForm form;
    
    @Autowired
    private UsuarioService service;
    
    @Override
    public UsuarioForm getForm() {
        
        return this.form;
    }

    @Override
    public UsuarioService getService() {
        
        return this.service;
    }   
}