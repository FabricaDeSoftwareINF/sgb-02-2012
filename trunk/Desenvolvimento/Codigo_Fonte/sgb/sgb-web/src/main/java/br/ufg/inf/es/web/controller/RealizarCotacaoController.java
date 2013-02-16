package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.RealizarCotacaoService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.web.controller.form.RealizarCotacaoForm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vinicius
 */
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
    public RealizarCotacaoForm getForm() {
        return form;
    }

    @Override
    public RealizarCotacaoService getService() {
        return service;
    }

}