/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope("session")
public class LivroController extends SGBController<Livro, LivroForm, LivroService> {

    @Autowired
    private LivroForm form;
    
    @Autowired
    private LivroService service;
    
    @Autowired
    private DisciplinaService disciplinaService;

    public LivroForm getForm() {
        return form;
    }

    public void setForm(LivroForm form) {
        this.form = form;
    }

    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }
    
    
    
}
