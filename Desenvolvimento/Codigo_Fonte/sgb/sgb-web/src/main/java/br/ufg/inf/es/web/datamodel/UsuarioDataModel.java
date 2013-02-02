/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 * Classe responsável por definir um modelo de tabela para Usuário selecionável dentro da DataTable.
 * @author Cássio Augusto Silva de Freitas
 */
public class UsuarioDataModel extends ListDataModel<Usuario> implements SelectableDataModel<Usuario>, Serializable {

    public UsuarioDataModel(List<Usuario> list) {
        super(list);
    }

    /**
     * Método responsável por obter os dados da linha da Tabela.
     * @param rowKey chave de identificação do objeto
     * @return Objeto da linha da tabela
     */
      @Override  
    public Usuario getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Usuario> autores = (List<Usuario>) getWrappedData();  
          
        for(Usuario autor : autores) {  
            
            if(String.valueOf(autor.getId()).equals(rowKey)) {
                
                return autor;  
            }
        }  
          
        return null;  
    }  
  
      /**
       * Método responsábel por obter a chave identificadora do objeto na linha selecionada.
       * @param autor
       * @return chave do objeto da linha da tabela
       */
    @Override  
    public Object getRowKey(Usuario autor) {  
        return autor.getId();  
    }  
    
}
