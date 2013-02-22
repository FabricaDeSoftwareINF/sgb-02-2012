package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ItemListaCompras;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author vinicius
 */
public class ItemListaCompraDataModel extends ListDataModel<ItemListaCompras> 
        implements SelectableDataModel<ItemListaCompras>, Serializable {    
  
    public ItemListaCompraDataModel() {  
    }  
   
    public ItemListaCompraDataModel(List<ItemListaCompras> data) {  
        super(data);  
    }  
      
    @Override  
    public ItemListaCompras getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<ItemListaCompras> livros = (List<ItemListaCompras>) getWrappedData();  
        ItemListaCompras livroSelecionado = null;
          
        for(ItemListaCompras livroParaCotacao : livros) {  
            if(String.valueOf(livroParaCotacao.getLivro().getId()).equals(rowKey)) {
                livroSelecionado = livroParaCotacao;  
            }
        } 
        return livroSelecionado;  
    }  
  
    @Override  
    public Object getRowKey(ItemListaCompras livro) {  
        return livro.getLivro().getId();  
    } 
}