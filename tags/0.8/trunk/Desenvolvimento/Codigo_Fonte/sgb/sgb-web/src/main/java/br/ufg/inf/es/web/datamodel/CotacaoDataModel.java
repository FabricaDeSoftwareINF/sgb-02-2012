
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.integracao.CotacaoService;
import br.ufg.inf.es.model.Cotacao;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Bruno Marquete
 */
public class CotacaoDataModel extends ListDataModel<Cotacao> implements SelectableDataModel<Cotacao>, Serializable {

    private Collection<Cotacao> datasource;
    private CotacaoService service;

    public CotacaoDataModel(List<Cotacao> list) {
        super(list);
    }

    public CotacaoDataModel(CotacaoService service) {
        this.service = service;
        this.datasource = service.list();
    }

    @Override
    public Object getRowKey(Cotacao cotacao) {
        return cotacao.getId();
    }

    @Override
    public Cotacao getRowData(String rowKey) {
        
        List<Cotacao> cotacoes = (List<Cotacao>) getWrappedData();  
          
        for(Cotacao cotacao : cotacoes) {  
            
            if(String.valueOf(cotacao.getId()).equals(rowKey)) {
                
                return cotacao;  
            }
        }  
          
        return null;
        
    }

    public Collection<Cotacao> getDatasource() {
        return datasource;
    }

    public void setDatasource(Collection<Cotacao> datasource) {
        this.datasource = datasource;
    }

    public CotacaoService getService() {
        return service;
    }

    public void setService(CotacaoService service) {
        this.service = service;
    }
}
