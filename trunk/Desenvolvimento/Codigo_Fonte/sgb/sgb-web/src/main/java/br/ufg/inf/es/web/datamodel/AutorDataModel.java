/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.AutorDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 * Classe responsável por moldar uma coleção de Autores para uma tabela selecionáve
 * do Primefaces
 * @author Cassio Augusto Silva de Freitas
 */
public class AutorDataModel extends ListDataModel<AutorDTO> implements SelectableDataModel<AutorDTO>,Serializable{
    
     public AutorDataModel() {  
    }  
  
    public AutorDataModel(List<AutorDTO> data) {  
        super(data);  
    }  
      
    @Override  
    public AutorDTO getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<AutorDTO> autores = (List<AutorDTO>) getWrappedData();  
          
        for(AutorDTO autor : autores) {  
            
            if(String.valueOf(autor.getId()).equals(rowKey)) {
                
                return autor;  
            }
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(AutorDTO autor) {  
        return autor.getId();  
    }  
    
}
