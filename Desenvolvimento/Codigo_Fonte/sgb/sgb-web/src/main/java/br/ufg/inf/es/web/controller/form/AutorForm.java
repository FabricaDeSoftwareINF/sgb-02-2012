package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.web.datamodel.AutorDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira, Cássio Augusto
 */
@Component
@Scope("session")
public class AutorForm extends GenericForm<Autor> {
    
    private String filtroNome;
    
    private AutorDataModel model;
    
    private Autor[] autoresSelecionados;
       
    private Collection<String> nomesSelecionados;
    
    /*Todos os autores*/
    private Collection<Autor> todosAutores;

    public String getFiltroNome() {
        return filtroNome;
    }

    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    public Autor[] getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(Autor[] autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }
    
    public AutorDataModel getModel() {
       
        List<Autor> autores =  new ArrayList<Autor>(this.getTodosAutores());
        
        model = new AutorDataModel(autores);
      
        return model;
    }

    public void setModel(AutorDataModel model) {
        this.model = model;
    }
    
    public Collection<Autor> getTodosAutores() {
        return todosAutores;
    }

    public void setTodosAutores(Collection<Autor> todosAutores) {
        this.todosAutores = todosAutores;
    }
   
    public Collection<String> getNomesSelecionados() {
        return nomesSelecionados;
    }

    public void setNomesSelecionados(Collection<String> nomesSelecionados) {
        this.nomesSelecionados = nomesSelecionados;
    }
        
}