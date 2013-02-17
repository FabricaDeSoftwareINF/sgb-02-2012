package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ListaCotacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Bruno Marquete
 */
public class ListaCotacaoDataModel extends ListDataModel<ListaCotacao> implements SelectableDataModel<ListaCotacao>, Serializable {

    public ListaCotacaoDataModel(List<ListaCotacao> list) {
        super(list);
    }

    @Override
    public Object getRowKey(ListaCotacao listaCotacao) {
        return listaCotacao.getId();
    }

    @Override
    public ListaCotacao getRowData(String rowKey) {
        
        List<ListaCotacao> listasCotacoes = (List<ListaCotacao>) getWrappedData();  
          
        for(ListaCotacao listaCotacao : listasCotacoes) {  
            
            if(String.valueOf(listaCotacao.getId()).equals(rowKey)) {
                
                return listaCotacao;  
            }
        }  
          
        return null;
        
    }

}
