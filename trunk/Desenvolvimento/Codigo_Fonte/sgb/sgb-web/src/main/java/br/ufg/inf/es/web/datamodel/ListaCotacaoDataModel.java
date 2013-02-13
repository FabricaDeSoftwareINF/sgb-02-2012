
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.model.ListaCotacao;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Bruno Marquete
 */
public class ListaCotacaoDataModel extends ListDataModel<ListaCotacao> implements SelectableDataModel<ListaCotacao>, Serializable {

    private Collection<ListaCotacao> datasource;
    private ListaCotacaoService service;

    public ListaCotacaoDataModel(List<ListaCotacao> list) {
        super(list);
    }

    public ListaCotacaoDataModel(ListaCotacaoService service) {
        this.service = service;
        this.datasource = service.list();
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

    public Collection<ListaCotacao> getDatasource() {
        return datasource;
    }

    public void setDatasource(Collection<ListaCotacao> datasource) {
        this.datasource = datasource;
    }

    public ListaCotacaoService getService() {
        return service;
    }

    public void setService(ListaCotacaoService service) {
        this.service = service;
    }
    
}
