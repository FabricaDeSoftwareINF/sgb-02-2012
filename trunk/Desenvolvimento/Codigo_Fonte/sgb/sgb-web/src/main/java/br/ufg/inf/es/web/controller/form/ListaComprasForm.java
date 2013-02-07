package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
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
        this.selectedLivros = selectedLivros;
    }

    
    
    
}