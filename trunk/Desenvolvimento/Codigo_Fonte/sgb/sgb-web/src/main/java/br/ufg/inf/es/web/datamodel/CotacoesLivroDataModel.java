
package br.ufg.inf.es.web.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.ufg.inf.es.model.CotacoesLivro;

/**
 *
 * @author Bruno Marquete
 */
public class CotacoesLivroDataModel extends ListDataModel<CotacoesLivro> implements SelectableDataModel<CotacoesLivro>, Serializable {

    public CotacoesLivroDataModel() {  
    }  
   
    public CotacoesLivroDataModel(List<CotacoesLivro> data) {  
        super(data);  
    }  
      
    @Override  
    public CotacoesLivro getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<CotacoesLivro> livros = (List<CotacoesLivro>) getWrappedData();  
        CotacoesLivro cotacaoSelecionada = null;
          
        for(CotacoesLivro cotacoesLivro : livros) {  
            if(cotacoesLivro.getLivro().toString().equals(rowKey)) {
                cotacaoSelecionada = cotacoesLivro;  
            }
        } 
        return cotacaoSelecionada;  
    }  
  
    @Override  
    public Object getRowKey(CotacoesLivro cotacoesLivro) {  
        return cotacoesLivro.getLivro();  
    } 

}
