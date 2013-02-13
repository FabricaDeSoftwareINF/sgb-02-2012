package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Editora;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class EditoraForm extends GenericForm<Editora> {
    private Editora editoraSelecionada;
    private Boolean exibirDialogExclusao;

    public Editora getEditoraSelecionada() {
        return editoraSelecionada;
    }

    public void setEditoraSelecionada(Editora editoraSelecionada) {
        this.editoraSelecionada = editoraSelecionada;
    }
    
    
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }
}