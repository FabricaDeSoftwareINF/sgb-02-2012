package br.ufg.inf.es.web.controller.form;

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

    private Collection<ListaCotacao> tabelaListaCotacoes;
    private ListaCotacaoDataModel listaCotacaoDataModel;
    private ListaCotacao[] listasCotacoesSelecionadas;

    public ListaCotacaoDataModel getListaCotacaoDataModel() {

        List<ListaCotacao> listasCotacoes = new ArrayList<ListaCotacao>(this.getTabelaListaCotacoes());
        listaCotacaoDataModel = new ListaCotacaoDataModel(listasCotacoes);

        return listaCotacaoDataModel;
    }

    public ListaCotacao[] getListasCotacoesSelecionadas() {
        return listasCotacoesSelecionadas;
    }

    public void setListasCotacoesSelecionadas(ListaCotacao[] listasCotacoesSelecionadas) {
        this.listasCotacoesSelecionadas = listasCotacoesSelecionadas;
    }

    public Collection<ListaCotacao> getTabelaListaCotacoes() {
        return tabelaListaCotacoes;
    }

    public void setTabelaListaCotacoes(Collection<ListaCotacao> tabelaListaCotacoes) {
        this.tabelaListaCotacoes = tabelaListaCotacoes;
    }
}
