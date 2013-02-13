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
public class ListaComprasDataModel extends ListDataModel<ListaCompras> implements SelectableDataModel<ListaCompras>, Serializable {    
  
    public ListaComprasDataModel() {  
    }  
   
    public ListaComprasDataModel(List<ListaCompras> data) {  
        super(data);  
    }  
      
    @Override
    public Object getRowKey(ListaCompras lc) {
        return lc.getId();
    }

    @Override
    public ListaCompras getRowData(String rowKey) {
        List<ListaCompras> lcs = (List<ListaCompras>) getWrappedData();  
          
        for(ListaCompras lc : lcs) { 
            
            if(lc.getId().toString().equals(rowKey)){  
                return lc;  
            }
        } 
        return null;  
    }
}