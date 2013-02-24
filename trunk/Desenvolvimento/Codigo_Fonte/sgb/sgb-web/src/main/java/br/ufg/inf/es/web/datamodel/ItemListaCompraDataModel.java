package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ItemListaCompras;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author vinicius
 */
public class ItemListaCompraDataModel extends ListDataModel<ItemListaCompras>
        implements SelectableDataModel<ItemListaCompras>, Serializable {

    /**
     * Construtor padrao
     */
    public ItemListaCompraDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public ItemListaCompraDataModel(List<ItemListaCompras> data) {
        super(data);
    }

    /**
     * Obtem o objeto com o id correspondente
     *
     * @param rowKey id do objeto desejado
     * @return objeto com o id
     */
    @Override
    public ItemListaCompras getRowData(String rowKey) {
        List<ItemListaCompras> livros = (List<ItemListaCompras>) getWrappedData();
        ItemListaCompras livroSelecionado = null;

        for (ItemListaCompras livroParaCotacao : livros) {
            if (String.valueOf(livroParaCotacao.getLivro().getId()).equals(rowKey)) {
                livroSelecionado = livroParaCotacao;
            }
        }
        return livroSelecionado;
    }

    /**
     * obtem o id do objeto
     *
     * @param autor
     * @return
     */
    @Override
    public Object getRowKey(ItemListaCompras livro) {
        return livro.getLivro().getId();
    }
}