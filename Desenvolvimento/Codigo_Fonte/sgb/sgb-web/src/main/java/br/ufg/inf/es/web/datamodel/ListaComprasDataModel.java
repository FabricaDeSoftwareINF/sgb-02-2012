package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ListaCompras;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author jackeline
 */
public class ListaComprasDataModel extends ListDataModel<ListaCompras>
        implements SelectableDataModel<ListaCompras>, Serializable {

    /**
     * Construtor padrao
     */
    public ListaComprasDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public ListaComprasDataModel(List<ListaCompras> data) {
        super(data);
    }

    /**
     * obtem o id do objeto
     *
     * @param autor
     * @return
     */
    @Override
    public Object getRowKey(ListaCompras lc) {
        return lc.getId();
    }

    /**
     * Obtem o objeto com o id correspondente
     *
     * @param rowKey id do objeto desejado
     * @return objeto com o id
     */
    @Override
    public ListaCompras getRowData(String rowKey) {
        List<ListaCompras> lcs = (List<ListaCompras>) getWrappedData();

        for (ListaCompras lc : lcs) {

            if (lc.getId().toString().equals(rowKey)) {
                return lc;
            }
        }
        return null;
    }
}