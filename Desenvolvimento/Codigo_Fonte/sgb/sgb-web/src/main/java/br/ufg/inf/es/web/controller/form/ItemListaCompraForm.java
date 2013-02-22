package br.ufg.inf.es.web.controller.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.web.datamodel.ItemListaCompraDataModel;

/**
 *
 * @author Victor Carvalho
 */
@Component
@Scope("session")
public class ItemListaCompraForm extends GenericForm<ItemListaCompras> {

    private Collection<ItemListaCompras> todosLivros;
    private ItemListaCompraDataModel livroDM;
    
    public Collection<ItemListaCompras> getTodosLivros() {
        
        return todosLivros;
    }

    public void setTodosLivros(Collection<ItemListaCompras> todosLivros) {
        
        this.todosLivros = todosLivros;
    }
    
    public ItemListaCompraDataModel getLivroDataModel() {
        
        List<ItemListaCompras> livros =  new ArrayList<ItemListaCompras>(this.getTodosLivros());
        
        livroDM = new ItemListaCompraDataModel(livros);
        
        return livroDM;
    }

    public void setLivroDataModel(ItemListaCompraDataModel livroDM) {
        this.livroDM = livroDM;
    }
}
