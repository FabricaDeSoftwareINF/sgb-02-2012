package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import br.ufg.inf.es.web.datamodel.ListaComprasDataModel;
import br.ufg.inf.es.web.datamodel.LivroParaCotacaoDataModel;
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
    private Collection<LivroParaCotacao> livrosSelecionados;
    private Collection<LivroParaCotacao> todosLivros;
    private LivroParaCotacaoDataModel livroDM;
    private ListaComprasDataModel listaComprasDM;
    private String filtroTitulo;

    public Collection<ListaCompras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(Collection<ListaCompras> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public Collection<LivroParaCotacao> getLivrosSelecionados() {
        return this.livrosSelecionados;
    }

    public void setLivrosSelecionados(Collection<LivroParaCotacao> selectedLivros) {
        this.livrosSelecionados = selectedLivros;
    }

    public Collection<LivroParaCotacao> getTodosLivros() {

        return todosLivros;
    }

    public void setTodosLivros(Collection<LivroParaCotacao> todosLivros) {

        this.todosLivros = todosLivros;
    }

    public LivroParaCotacaoDataModel getLivroDataModel() {

        List<LivroParaCotacao> livros = new ArrayList<LivroParaCotacao>(this.getTodosLivros());

        livroDM = new LivroParaCotacaoDataModel(livros);

        return livroDM;
    }

    public void setLivroDataModel(LivroParaCotacaoDataModel livroDM) {
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
