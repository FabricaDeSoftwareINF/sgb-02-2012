package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.web.controller.form.EditoraForm;
import java.util.Collection;
import java.util.HashSet;
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

    private String buscaEditora = "";
    private Collection<Editora> editoraSelecionado = new HashSet<Editora>();
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

    public String salvarEditora() throws ValidationException {

        super.insert();

        return super.openSearchPage();

    }

    public void selecionaEditora(Editora assTab) {
        
        this.editoraSelecionado.add(assTab);
        
    }

    public void removerEditoraSelecionadas() {
        
        this.service.getDAO().removeAll(editoraSelecionado);  
        
    }
    
    public String getBuscaEditora() {
        return buscaEditora;
    }

    public void setBuscaEditora(String buscaEditora) {
        this.buscaEditora = buscaEditora;
    }

    public Collection<Editora> getEditoraSelecionado() {
        return editoraSelecionado;
    }

    public void setEditoraSelecionado(Collection<Editora> editoraSelecionado) {
        this.editoraSelecionado = editoraSelecionado;
    }
}