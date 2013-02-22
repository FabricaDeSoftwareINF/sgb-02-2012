package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.web.datamodel.ListaComprasDataModel;
import br.ufg.inf.es.web.datamodel.ItemListaCompraDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jackeline Neves
 */
@Component
@Scope("session")
public class ListaComprasForm extends GenericForm<ListaCompras> {

    private Collection<ListaCompras> listaCompras;
    private Collection<ItemListaCompras> livrosSelecionados;
    private Collection<ItemListaCompras> todosLivros;
    private ItemListaCompraDataModel itemListaComprasDataModel;
    private ListaComprasDataModel listaComprasDM;
    private String filtroTitulo;
    private String nomeLista;    
    private ListaCompras listaComprasParaRemocao; 


    public Collection<ListaCompras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(Collection<ListaCompras> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public Collection<ItemListaCompras> getLivrosSelecionados() {
        return this.livrosSelecionados;
    }

    public void setLivrosSelecionados(Collection<ItemListaCompras> selectedLivros) {
        this.livrosSelecionados = selectedLivros;
    }

    public Collection<ItemListaCompras> getTodosLivros() {

        return todosLivros;
    }

    public void setTodosLivros(Collection<ItemListaCompras> todosLivros) {

        this.todosLivros = todosLivros;
    }

    public ItemListaCompraDataModel getItemListaDataModel() {
        return itemListaComprasDataModel;
    }

    public void setItemListaDataModel(ItemListaCompraDataModel livroDM) {
        this.itemListaComprasDataModel = livroDM;
    }

    public String getFiltroTitulo() {

        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {

        this.filtroTitulo = filtroTitulo;
    }

    public ListaComprasDataModel getListaComprasDM() {
        return listaComprasDM;
    }

    public void setListaComprasDM(ListaComprasDataModel lcDM) {
        this.listaComprasDM = lcDM;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
    

    public ItemListaCompraDataModel getLivroDM() {
        return itemListaComprasDataModel;
    }

    public void setLivroDM(ItemListaCompraDataModel livroDM) {
        this.itemListaComprasDataModel = livroDM;
    }

    public ListaCompras getListaComprasParaRemocao() {
        
        return listaComprasParaRemocao;
    }

    public void setListaComprasParaRemocao(ListaCompras listaComprasParaRemocao) {
        
        this.listaComprasParaRemocao = listaComprasParaRemocao;
    }

}
