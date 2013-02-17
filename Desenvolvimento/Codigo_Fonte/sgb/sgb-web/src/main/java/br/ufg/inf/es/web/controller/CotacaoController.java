
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.CotacaoService;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.web.controller.form.CotacaoForm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe controladora para realização da cotação.
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class CotacaoController extends SGBController<CotacoesLivro, CotacaoForm, CotacaoService> {

    /** Campo CotacaoForm */
    @Autowired
    private CotacaoForm form;
    
    @Autowired
    private CotacaoService service;

    @Override
    public void openInitialView() {
        
        this.getForm().setTabelaCotacoes(new ArrayList<CotacoesLivro>());
        
        this.getForm().getTabelaCotacoes().addAll(this.getService().list());
        
        this.getForm().setLivrosCotacao(this.getService().listarLivros());
    }
    
    
    @Override
    public CotacaoForm getForm() {
        return form;
    }

    @Override
    public CotacaoService getService() {
        return service;
    }

}