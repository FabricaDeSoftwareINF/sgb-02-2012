package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.web.controller.form.EditoraForm;
import java.util.ArrayList;
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

    public static final String KEY_MSG_SUCESSO = "arquitetura.msg.sucesso";
    private String buscaEditora = "";
    private Collection<Editora> editoraSelecionado = new ArrayList<Editora>();
    private Editora editora = new Editora();
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

    /**
     * Método responsável por salvar uma nova Editora no banco de dados
     *
     * @return String de navegação para página Inicial
     * @throws ValidationException
     */
    public String salvarEditora() {

        try {

            this.getService().insert(this.getForm().getEntity());

            this.addSuccessMessage(EditoraController.KEY_MSG_SUCESSO);

        } catch (ValidationException ve) {

            addWarningMessage(ve.getKeyMessage());

        }

        return super.openSearchPage();


    }

    /**
     * Método responsável por editar uma Editora no banco de dados
     *
     * @return String de navegação para página inicial de Editora
     */
    public String editarEditora() {

        try {
            
            this.getForm().getCollectionEntities().remove(this.getForm().getEntity());

            this.getService().editar(this.getForm().getEntity());

            addSuccessMessage(EditoraController.KEY_MSG_SUCESSO);

        } catch (ValidationException ve) {
            
            this.getService().getDAO().closeSession();
           
            addWarningMessage(ve.getKeyMessage());

        }

        return super.openSearchPage();

    }

    public void selecionaEditora(Editora assTab) {

        if (this.editoraSelecionado.contains(assTab)) {

            this.editoraSelecionado.remove(assTab);

        } else {

            this.editoraSelecionado.add(assTab);

        }

    }

    public void preparaEditora(Editora assTab) {

        this.editora = assTab;

    }

    public String removerEditora() {

        this.service.getDAO().remove(this.editora);

        return super.openSearchPage();

    }

    public String removerEditoraSelecionadas() {

        if (!editoraSelecionado.isEmpty()) {

            this.service.getDAO().removeAll(editoraSelecionado);

            editoraSelecionado = new ArrayList<Editora>();

            this.form.setCollectionEntities(this.service.getDAO().list());

         

        } else {
            
            this.addWarningMessage("label.nenhumRegistroSelecionado");
            
        }

           return super.openSearchPage();
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

    public Editora getEditora() {

        return editora;

    }

    public void setEditora(Editora editora) {

        this.editora = editora;

    }
}