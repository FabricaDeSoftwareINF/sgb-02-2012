package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /** Campo CotacaoForm */
    @Autowired
    private RealizarCotacaoForm form;
    
    @Autowired
    private RealizarCotacaoService service;
    
    @Autowired
    private LivroService livroService;
    
    @Override
    public String openInitialPage(){
        
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
        
        ListaCotacao listaCotacao = this.getService().realizarCotacao(Arrays.asList(livrosSelecionados));
        
        List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>(listaCotacao.getCotacoesLivro());
        
        this.getForm().setCotacoesDataModel(new CotacaoDataModel(cotacoes));
        
        this.getForm().setCotacoesSelecionadas(cotacoes);
        
        return this.openInsertPage();
    }
    
    public String realizarCotacaoListaCompras() {
        
        if(UtilObjeto.isReferencia(this.getForm().getListaCompras()) && UtilObjeto.isReferencia(this.getForm().getListaCompras().getLivrosDaListaCompras())){
        
            ListaCotacao listaCotacao = this.getService().realizarCotacao(this.getForm().getListaCompras().getLivrosDaListaCompras());

            List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>(listaCotacao.getCotacoesLivro());

            this.getForm().setCotacoesDataModel(new CotacaoDataModel(cotacoes));

            this.getForm().setCotacoesSelecionadas(cotacoes);
        
            return this.openInsertPage();
        }
        
        return "";
    }
    
    public String salvarListaCotacao() {
        
        this.getService().salvarListaCotacao(this.getForm().getCotacoesSelecionadas(), 
                this.getForm().getNomeLista());
        
        return this.openInitialPage();
    }
    
}