package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livraria;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import java.util.ArrayList;
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
    public void openInitialView() {
        
        this.getForm().setTabelaCotacoes(new ArrayList<Cotacao>());
        
        this.getForm().setLivrosCotacao(livroService.list());
    }

    @Override
    public void openInsertView() {
     
        Cotacao cotacao = new Cotacao();
        
        cotacao.setLivro(this.getService().getLivroDao().find(163840l)); 
        
        cotacao.setQuantidade(3);
        
        cotacao.setValor(79.5);
        
        Livraria livraria = new Livraria();
        
        livraria.setNome("Barnes & Noble");
        
        livraria.setSite("http://barnesenobles.com");
        
        livraria.setUrlLogo("http://1.bp.blogspot.com/-n-fsjwiesiw/Tr7UmKfH6JI/AAAAAAAAAXQ/PUbQNNaU0bY/s1600/fundacao.jpg");
                
        this.getForm().getCotacoesRealizadas().add(cotacao);
        
    }
    
    @Override
    public RealizarCotacaoForm getForm() {
        return form;
    }

    @Override
    public RealizarCotacaoService getService() {
        return service;
    }

}