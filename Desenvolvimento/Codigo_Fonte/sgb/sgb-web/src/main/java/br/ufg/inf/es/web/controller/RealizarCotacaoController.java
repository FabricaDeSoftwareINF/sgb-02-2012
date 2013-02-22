package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import br.ufg.inf.es.web.datamodel.CotacoesLivroDataModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
@Scope("session")
public class RealizarCotacaoController extends SGBController<ListaCotacao, RealizarCotacaoForm, RealizarCotacaoService> {

    /**
     * Campo CotacaoForm
     */
    @Autowired
    private RealizarCotacaoForm form;
    @Autowired
    private RealizarCotacaoService service;
    @Autowired
    private LivroService livroService;
    
    List<CotacoesLivro> listaCotacoesLivro;

    @Override
    public String openInitialPage() {

        LivroDataModel livroDataModel = new LivroDataModel((List) livroService.list());

        this.getForm().setLivroDataModel(livroDataModel);

        return super.openInitialPage();
    }

    @Override
    public RealizarCotacaoForm getForm() {

        return this.form;
    }

    @Override
    public RealizarCotacaoService getService() {

        return this.service;
    }

    public String realizarCotacao() {

        Livro[] livrosSelecionados = this.getForm().getLivrosSelecionados();
        
        List<ItemListaCompras> listaLivroListaCotacoes = new ArrayList<ItemListaCompras>();
        
        for (Livro livro : livrosSelecionados) {
            ItemListaCompras livroListaCotacao = new ItemListaCompras();
            livroListaCotacao.setLivro(livro);
            listaLivroListaCotacoes.add(livroListaCotacao);
        }

        ListaCotacao listaCotacao = this.getService().realizarCotacao(listaLivroListaCotacoes);

        List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>(listaCotacao.getCotacoesLivro());

        this.listaCotacoesLivro = cotacoes;
        
        this.getForm().setCotacoesDataModel(new CotacoesLivroDataModel(cotacoes));

        return this.openInsertPage();
    }

    public String realizarCotacaoListaCompras() {

        if (UtilObjeto.isReferencia(this.getForm().getListaCompras()) && UtilObjeto.isReferencia(this.getForm().getListaCompras().getLivrosDaListaCompras())) {

            ListaCotacao listaCotacao = this.getService().realizarCotacao(this.getForm().getListaCompras().getLivrosDaListaCompras());

            List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>(listaCotacao.getCotacoesLivro());

            this.getForm().setCotacoesDataModel(new CotacoesLivroDataModel(cotacoes));
            
            CotacoesLivro[] cotacoesArray = cotacoes.toArray(new CotacoesLivro[cotacoes.size()]);

            this.getForm().setCotacoesSelecionadas(cotacoesArray);

            return this.openInsertPage();
        }

        return "";
    }

    public String salvarListaCotacao() {
        this.getService().salvarListaCotacao(listaCotacoesLivro,
                this.getForm().getNomeLista());

        return this.openInitialPage();
    }

    public StreamedContent buscaImagem() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String imagem = externalContext.getRequestParameterMap().get("imagem");
        StreamedContent sc = null;
        try {
            sc = new DefaultStreamedContent(new URL(imagem).openStream());
        } catch (IOException ex) {
            Logger.getLogger(RealizarCotacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sc;
    }
    
    public void removerCotacoesLivro() {
        List<CotacoesLivro> remover = Arrays.asList(this.getForm().getCotacoesSelecionadas());
        listaCotacoesLivro.removeAll(remover);
    }
}