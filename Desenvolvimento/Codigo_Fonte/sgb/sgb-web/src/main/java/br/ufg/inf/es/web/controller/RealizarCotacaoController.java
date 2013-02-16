package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livraria;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    public void openInsertView() {
     
//        Cotacao cotacao = new Cotacao();
//        
//        Livro livro = this.getService().getLivroDao().find(163840l);
//        
//        livro.setAutores((Collection<Autor>) this.getService().getLivroDao().getAutores(livro.getId()));
//        
//        cotacao.setLivro(livro);
//              
//        cotacao.setQuantidade(3);
//        
//        cotacao.setValor(79.5);
//        
//        Livraria livraria = new Livraria();
//        
//        livraria.setNome("Barnes & Noble");
//        
//        livraria.setSite("http://barnesenobles.com");
//        
//        livraria.setUrlLogo("http://1.bp.blogspot.com/-n-fsjwiesiw/Tr7UmKfH6JI/AAAAAAAAAXQ/PUbQNNaU0bY/s1600/fundacao.jpg");
//        
//        List<Cotacao> cotacoes = new ArrayList<Cotacao>();
//        
//        cotacoes.add(cotacao);
//        
//        this.getForm().setCotacoesDataModel(new CotacaoDataModel(cotacoes));
        
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
        
        return this.openInsertPage();
    }
    
}