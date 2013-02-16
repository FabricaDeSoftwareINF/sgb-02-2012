
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.integracao.CotacaoService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.CotacoesLivro;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Bruno Marquete
 */
public class CotacaoDataModel extends ListDataModel<CotacoesLivro> implements SelectableDataModel<CotacoesLivro>, Serializable {

    private Collection<CotacoesLivro> datasource;
    private CotacaoService service;

    public CotacaoDataModel(List<CotacoesLivro> list) {
        super(list);
    }

    public CotacaoDataModel(CotacaoService service) {
        this.service = service;
        this.datasource = service.list();
    }

    @Override
    public Object getRowKey(CotacoesLivro cotacao) {
        return cotacao.getId();
    }

    @Override
    public CotacoesLivro getRowData(String rowKey) {
        
        List<CotacoesLivro> cotacoes = (List<CotacoesLivro>) getWrappedData();  
          
        for(CotacoesLivro cotacao : cotacoes) {  
            
            if(String.valueOf(cotacao.getId()).equals(rowKey)) {
                
                return cotacao;  
            }
        }  
          
        return null;
        
    }

    public Collection<CotacoesLivro> getDatasource() {
        return datasource;
    }

    public void setDatasource(Collection<CotacoesLivro> datasource) {
        this.datasource = datasource;
    }

    public CotacaoService getService() {
        return service;
    }

    public void setService(CotacaoService service) {
        this.service = service;
    }

}
