package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.ListaComprasDataModel;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
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
    
    private Livro[] selectedLivros;
    
    private Collection<Livro> todosLivros;
    
    private LivroDataModel livroDM;
    
    private ListaComprasDataModel listaComprasDM;
    
    private String filtroTitulo;

    public Collection<ListaCompras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(Collection<ListaCompras> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public Livro[] getSelectedLivros() {
        
        Livro[] retorno = null;
        
        if(this.selectedLivros != null) {
            
             retorno = this.selectedLivros.clone();
        }
        
        return retorno;
    }

    public void setSelectedLivros(Livro[] selectedLivros) {
        
        if(selectedLivros != null) {
        
            this.selectedLivros = (Livro[]) selectedLivros.clone();
        }
    }

    public Collection<Livro> getTodosLivros() {
        
        return todosLivros;
    }

    public void setTodosLivros(Collection<Livro> todosLivros) {
        
        this.todosLivros = todosLivros;
    }
    
    public LivroDataModel getLivroDM() {
        
        List<Livro> livros =  new ArrayList<Livro>(this.getTodosLivros());
        
        livroDM = new LivroDataModel(livros);
        
        return livroDM;
    }

    public void setLivroDM(LivroDataModel livroDM) {
        this.livroDM = livroDM;
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
}
