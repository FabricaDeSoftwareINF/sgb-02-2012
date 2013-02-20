package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author vinicius
 */
public class LivroParaCotacaoDataModel extends ListDataModel<LivroParaCotacao> 
        implements SelectableDataModel<LivroParaCotacao>, Serializable {    
  
    public LivroParaCotacaoDataModel() {  
    }  
   
    public LivroParaCotacaoDataModel(List<LivroParaCotacao> data) {  
        super(data);  
    }  
      
    @Override  
    public LivroParaCotacao getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<LivroParaCotacao> livros = (List<LivroParaCotacao>) getWrappedData();  
        LivroParaCotacao livroSelecionado = null;
          
        for(LivroParaCotacao livro : livros) {  
            if(String.valueOf(livro.getId()).equals(rowKey)) {
                livroSelecionado = livro;  
            }
        } 
        return livroSelecionado;  
    }  
  
    @Override  
    public Object getRowKey(LivroParaCotacao car) {  
        return car.getId();  
    } 
}