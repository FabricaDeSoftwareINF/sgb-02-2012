package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.web.datamodel.EditoraDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class EditoraForm extends GenericForm<Editora> {

    private Editora editoraSelecionada;
    private Boolean exibirDialogExclusao;
    private EditoraDataModel model;
    private Collection<Editora> colecaoRemocao;

    public Collection<Editora> getColecaoRemocao() {
        return colecaoRemocao;
    }

    public void setColecaoRemocao(Collection<Editora> colecaoRemocao) {
        this.colecaoRemocao = colecaoRemocao;
    }

    public EditoraDataModel getModel() {

        List<Editora> lista = new ArrayList<Editora>(this.getCollectionEntities());

        this.model = new EditoraDataModel(lista);

        return model;
    }

    public void setModel(EditoraDataModel model) {
        this.model = model;
    }

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