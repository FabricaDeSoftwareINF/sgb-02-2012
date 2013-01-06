package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.web.controller.form.AutorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class AutorController
        extends SGBController<Autor, AutorForm, AutorService> {

    @Autowired
    private AutorForm form;
    
    @Autowired
    private AutorService service;

    @Override
    public String openInitialPage() {
      
        buscaTodosAutores();
       
        return super.openInitialPage();
    }
    
    
    
   /**
    * Método responsável por buscar todos os autores do banco de dados e colocar
    * a coleção no formulário da estória de usuário.
    * 
    * @author Cássio Augusto Silva de Freitas
    */
    public void buscaTodosAutores() {
        
        this.getForm().setTodosAutores(this.getService().buscaTodosAutores(this.getForm().getFiltroNome()));
        
        this.getForm().setFiltroNome("");
    }
    
     @Override
    public AutorForm getForm() {

        return this.form;
    }

    @Override
    public AutorService getService() {

        return this.service;
    }

    public void setForm(AutorForm form) {
        
        this.form = form;
    }

    public void setService(AutorService service) {
        
        this.service = service;
    }
    
    
}