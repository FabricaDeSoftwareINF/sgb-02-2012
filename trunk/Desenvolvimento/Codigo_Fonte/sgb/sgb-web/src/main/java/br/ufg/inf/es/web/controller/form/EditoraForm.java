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

    /**
     * Obtem a editora selecionada
     *
     * @return
     */
    public Editora getEditoraSelecionada() {
        return editoraSelecionada;
    }

    /**
     * Define a editora selecionada
     *
     * @param editoraSelecionada
     */
    public void setEditoraSelecionada(Editora editoraSelecionada) {
        this.editoraSelecionada = editoraSelecionada;
    }

    /**
     * Obtem o valor que define a exibicao do dialog de exclusao
     *
     * @return
     */
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    /**
     * Define um novo valor para o dialog de exlusao
     *
     * @param exibirDialogExclusao
     */
    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }
}