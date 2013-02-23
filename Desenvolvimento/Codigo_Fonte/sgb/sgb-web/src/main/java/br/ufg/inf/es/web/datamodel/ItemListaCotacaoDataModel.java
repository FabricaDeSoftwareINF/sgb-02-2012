package br.ufg.inf.es.web.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.ufg.inf.es.model.ItemListaCotacao;

/**
 *
 * @author Bruno Marquete
 */
public class ItemListaCotacaoDataModel extends ListDataModel<ItemListaCotacao>
        implements SelectableDataModel<ItemListaCotacao>, Serializable {

    public ItemListaCotacaoDataModel() {
    }

    public ItemListaCotacaoDataModel(List<ItemListaCotacao> data) {
        super(data);
    }

    @Override
    public ItemListaCotacao getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<ItemListaCotacao> livros = (List<ItemListaCotacao>) getWrappedData();
        ItemListaCotacao cotacaoSelecionada = null;

        for (ItemListaCotacao cotacoesLivro : livros) {
            if (cotacoesLivro.getLivro().toString().equals(rowKey)) {
                cotacaoSelecionada = cotacoesLivro;
            }
        }
        return cotacaoSelecionada;
    }

    @Override
    public Object getRowKey(ItemListaCotacao cotacoesLivro) {
        return cotacoesLivro.getLivro();
    }
}
