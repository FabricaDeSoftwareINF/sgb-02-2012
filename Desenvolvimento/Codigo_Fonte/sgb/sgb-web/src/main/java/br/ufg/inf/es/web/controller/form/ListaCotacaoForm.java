package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.web.datamodel.ListaCotacaoDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class ListaCotacaoForm extends GenericForm<ListaCotacao> {

    private ListaCotacaoDataModel listaCotacaoDataModel;
    private Collection<ListaCotacao> listasSelecionadas = new ArrayList<ListaCotacao>();
    private Boolean exibirDialogExclusao;
    private List<ItemListaCotacao> listaOtimizada;
    private boolean tipoOtimizacao;
    private Double valorOrcamento;
    private Double valorTotalListaOtimizada;

    public Double getValorTotalListaOtimizada() {
        return valorTotalListaOtimizada;
    }

    public void setValorTotalListaOtimizada(Double valorTotalListaOtimizada) {
        this.valorTotalListaOtimizada = valorTotalListaOtimizada;
    }

    public ListaCotacaoDataModel getListaCotacaoDataModel() {
        return listaCotacaoDataModel;
    }

    public void setListaCotacaoDataModel(ListaCotacaoDataModel listaCotacaoDataModel) {

        this.listaCotacaoDataModel = listaCotacaoDataModel;
    }

    public Collection<ListaCotacao> getListasSelecionadas() {
        return listasSelecionadas;
    }

    public void setListasSelecionadas(Collection<ListaCotacao> listasSelecionadas) {
        this.listasSelecionadas = listasSelecionadas;
    }

    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }

    public boolean isTipoOtimizacao() {
        return tipoOtimizacao;
    }

    public void setTipoOtimizacao(boolean tipoOtimizacao) {
        this.tipoOtimizacao = tipoOtimizacao;
    }

    public Double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(Double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public List<ItemListaCotacao> getListaOtimizada() {
        return listaOtimizada;
    }

    public void setListaOtimizada(List<ItemListaCotacao> listaOtimizada) {
        this.listaOtimizada = listaOtimizada;
    }
}
