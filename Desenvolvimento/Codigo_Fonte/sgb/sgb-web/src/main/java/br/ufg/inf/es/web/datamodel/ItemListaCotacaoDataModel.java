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

    /**
     * Construtor padrao
     */
    public ItemListaCotacaoDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public ItemListaCotacaoDataModel(List<ItemListaCotacao> data) {
        super(data);
    }

    /**
     * Obtem o objeto com o id correspondente
     *
     * @param rowKey id do objeto desejado
     * @return objeto com o id
     */
    @Override
    public ItemListaCotacao getRowData(String rowKey) {
        List<ItemListaCotacao> livros = (List<ItemListaCotacao>) getWrappedData();
        ItemListaCotacao cotacaoSelecionada = null;

        for (ItemListaCotacao cotacoesLivro : livros) {
            if (String.valueOf(cotacoesLivro.getLivro().getId()).equals(rowKey)) {
                cotacaoSelecionada = cotacoesLivro;
            }
        }
        return cotacaoSelecionada;
    }

    /**
     * obtem o id do objeto
     *
     * @param autor
     * @return
     */
    @Override
    public Object getRowKey(ItemListaCotacao cotacoesLivro) {
        return cotacoesLivro.getLivro();
    }
}
