/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Autor;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class AutorDataModel extends ListDataModel<Autor> implements SelectableDataModel<Autor> {
    
     public AutorDataModel() {  
    }  
  
    public AutorDataModel(List<Autor> data) {  
        super(data);  
    }  
      
    @Override  
    public Autor getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Autor> autores = (List<Autor>) getWrappedData();  
          
        for(Autor autor : autores) {  
            if(String.valueOf(autor.getId()).equals(rowKey))  
                return autor;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Autor autor) {  
        return autor.getId();  
    }  
    
}
