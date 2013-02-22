package br.ufg.inf.es.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ItemListaCompraService;
import br.ufg.inf.es.integracao.ListaComprasService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.integracao.ParametrosService;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.web.controller.form.ListaComprasForm;
import br.ufg.inf.es.web.datamodel.ItemListaCompraDataModel;

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
    private UsuarioController usuarioController;
    @Autowired
    private ItemListaCompraService itemListaCompraService;
    
    @Autowired
    private ParametrosService parametrosService;
            
    private int parametroMec;
    
    private Collection<ItemListaCompras> itensListaCompra;

    /**
     * Método responsável por retornar a string de navegação para a pagina
     * incial da Estória de usuário buscar todos os livros.
     *
     * @return string de navegação
     */
    @Override
    public String openInitialPage() {
        this.getForm().setListaCompras(this.getService().list());
        
        List<ItemListaCompras> itens = new ArrayList<ItemListaCompras>();
        try {
            if (itensListaCompra == null) {
                itens = new ArrayList<ItemListaCompras>(itemListaCompraService.obtemLivrosParaCotacao());
            }
        } catch (ValidationException ex) {
            Logger.getLogger(ListaComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getForm().setItemListaDataModel(new ItemListaCompraDataModel(itens));
        return super.openInitialPage();
    }
    
    @Override
    public String openInsertPage() {
        buscaTodosLivros();
        this.getForm().setLivrosSelecionados(new ArrayList<ItemListaCompras>());
        try {
            parametroMec = parametrosService.obtenhaParametroMEC();
        } catch (ValidationException ex) {
            Logger.getLogger(ListaComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.openInsertPage();
    }
    
    @Override
    public String openEditPage(ListaCompras lista) {
        List<ItemListaCompras> itens = new ArrayList<ItemListaCompras>(lista.getLivrosDaListaCompras());
        this.getForm().setLivrosSelecionados(new ArrayList<ItemListaCompras>());
        this.getForm().setLivroDM(new ItemListaCompraDataModel(itens));
        try {
            parametroMec = parametrosService.obtenhaParametroMEC();
        } catch (ValidationException ex) {
            Logger.getLogger(ListaComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.openEditPage(lista);
    }

    @Override
    public ListaComprasForm getForm() {
        return this.form;
    }

    @Override
    public ListaComprasService getService() {
        return this.service;
    }

    public int getParametroMec() {
        return parametroMec;
    }

    public void setParametroMec(int parametroMec) {
        this.parametroMec = parametroMec;
    }
    
    /**
     * @author Jackeline
     */
    public void buscaTodosLivros() {
        try {
            this.getForm().setTodosLivros(itemListaCompraService.obtemLivrosParaCotacao());
        } catch (ValidationException ve) {
            this.addErrorMessage(ve.getKeyMessage());
        }
    }

    public String salvarListaCompras() throws ValidationException {

        List<ItemListaCompras> livros = new ArrayList<ItemListaCompras>();

        for (ItemListaCompras livroParaCotacao : this.getForm().getLivrosSelecionados()) {
            ItemListaCompras livroListaCotacao = new ItemListaCompras();
            livroListaCotacao.setLivro(livroParaCotacao.getLivro());
            livroListaCotacao.setQuantidadeAComprar(livroListaCotacao.getQuantidadeAComprar());
            livros.add(livroListaCotacao);
        }
        
        ListaCompras listaCompras = this.getForm().getEntity();

        listaCompras.setDataCriacao(new Date());

        listaCompras.setLivrosDaListaCompras(livros);

        for (ItemListaCompras llc : livros) {
            llc.getListaCompras().add(listaCompras);
        }

        listaCompras.setUser(usuarioController.getUsuarioLogado());
        this.getService().criaListaCompras(listaCompras);

        this.getForm().setListaCompras(this.getService().list());

        return this.openInitialPage();
    }
    
    public String editarListaCompras() throws ValidationException {
        this.service.save(this.getForm().getEntity());
        return this.openInitialPage();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        
        try {
            
            this.getService().remove(this.getForm().getListaComprasParaRemocao());
            
            this.getForm().setListaCompras(this.getService().list());
            
            this.addSuccessMessage("arquitetura.msg.sucesso");
            
        } catch (ValidationException ve){
            
            this.addErrorMessage("arquitetura.msg.erro");
        }
    }   
    
    public void removerLivros() {
        Collection<ItemListaCompras> livrosSelecionados = this.getForm().getLivrosSelecionados();
        Collection<ItemListaCompras> itens = this.getForm().getEntity().getLivrosDaListaCompras();
        itens.removeAll(livrosSelecionados);
        List<ItemListaCompras> itensList = new ArrayList<ItemListaCompras>(itens);
        this.getForm().setItemListaDataModel(new ItemListaCompraDataModel(itensList));
        this.getForm().setLivrosSelecionados(new ArrayList<ItemListaCompras>());
    }
}
