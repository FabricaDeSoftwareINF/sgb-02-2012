package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.*;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.web.controller.form.ListaComprasForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jackeline Neves
 */
@Component
@Scope("session")
public class ListaComprasController extends SGBController<ListaCompras, ListaComprasForm, ListaComprasService> {

    @Autowired
    private ListaComprasForm form;
    @Autowired
    private ListaComprasService service;
    @Autowired
    private LivroService livroService;
    @Autowired
    private LivroParaCotacaoService livroParaCotacaoService;
    private LivroDataModel livroDataModel;
    private Livro[] livrosSelecionados;

    /**
     * Método responsável por retornar a string de navegação para a pagina
     * incial da Estória de usuário buscar todos os livros.
     *
     * @return string de navegação
     */
    @Override
    public String openInitialPage() {

        this.getForm().setListaCompras(this.service.list());
        this.livroDataModel = new LivroDataModel((List) livroService.list());

        this.getService().carregarLivrosDaListaCompras(this.getForm().getListaCompras());
        this.getForm().setTodosLivros(livroParaCotacaoService.obtemLivrosParaCotacao());
        buscaTodosLivros();

        return super.openInitialPage();
    }

    @Override
    public ListaComprasForm getForm() {
        return this.form;
    }

    @Override
    public ListaComprasService getService() {
        return this.service;
    }

    public LivroDataModel getLivroDataModel() {
        return livroDataModel;
    }

    /**
     * @author Jackeline
     */
    public void buscaTodosLivros() {
        this.getForm().setTodosLivros(livroParaCotacaoService.obtemLivrosParaCotacao());
    }

    public Livro[] getLivrosSelecionados() {

        Livro[] retorno = null;

        if (this.livrosSelecionados != null) {

            retorno = this.livrosSelecionados.clone();
        }

        return retorno;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {

        if (this.livrosSelecionados != null) {

            this.livrosSelecionados = (Livro[]) livrosSelecionados.clone();
        }
    }

    public void salvarListaCompras() throws ValidationException {

        List<Livro> livros = new ArrayList<Livro>();
        
        for (LivroParaCotacao livroParaCotacao : this.getForm().getLivrosSelecionados()) {
            livros.add(livroParaCotacao.getLivro());
        }
        this.getService().criaListaCompras(livros);

        this.getForm().setListaCompras(this.getService().list());

    }
    
    /**
     * Cria opções para filtragem dos livros, no datatable, entre estrangeiros
     * ou não.
     *
     * @return Opções para filtragem dos livros, no datatable, entre
     * estrangeiros ou não.
     */
    public SelectItem[] getEstrangeiroOptions() {
        String simLabel = getBundle().getString("arquitetura.msg.sim");
        String naoLabel = getBundle().getString("arquitetura.msg.nao");
        SelectItem vazio = new SelectItem("");
        SelectItem sim = new SelectItem("true", simLabel);
        SelectItem nao = new SelectItem("false", naoLabel);
        return new SelectItem[]{vazio, sim, nao};
    }
    
}
