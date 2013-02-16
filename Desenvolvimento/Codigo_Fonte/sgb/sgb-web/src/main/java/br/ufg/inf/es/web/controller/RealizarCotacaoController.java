package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
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
        
        List<Cotacao> cotacoes = (List) this.service.realizarCotacao(Arrays.asList(livrosSelecionados));
        
        this.getForm().setCotacoesDataModel(new CotacaoDataModel(cotacoes));
        
        this.getForm().setCotacoesSelecionadas(cotacoes.toArray(new Cotacao[cotacoes.size()]));
        
        return this.openInsertPage();
    }
    
    public String salvarListaCotacao() {
        
        this.getService().salvarListaCotacao(this.getForm().getCotacoesSelecionadas());
        
        return null;
    }
    
}