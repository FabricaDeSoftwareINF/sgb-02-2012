package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Livro;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author vinicius
 */
public class LivroDataModel extends ListDataModel<Livro> implements SelectableDataModel<Livro> {    
  
    public LivroDataModel() {  
    }  
   
    public LivroDataModel(List<Livro> data) {  
        super(data);  
    }  
      
    @Override  
    public Livro getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Livro> livros = (List<Livro>) getWrappedData();  
          
        for(Livro livro : livros) {  
            if(livro.getId().equals(rowKey))  
                return livro;  
        } 
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Livro car) {  
        return car.getId();  
    } 
}