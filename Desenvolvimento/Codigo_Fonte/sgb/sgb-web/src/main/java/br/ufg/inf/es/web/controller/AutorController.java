package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.web.controller.form.AutorForm;
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
public class AutorController
        extends SGBController<Autor, AutorForm, AutorService> {

    @Autowired
    private AutorForm form;
    
    @Autowired
    private AutorService service;
    
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
    
    public Collection<Autor> complete(String query) {  
        Collection<Autor> results = new ArrayList<Autor>();
          
        for(Autor autor : form.getCollectionEntities()){
            if(autor.getNome().contains(query)){
                results.add(autor);
            }
        }
          
        return results;  
    }  
    
}