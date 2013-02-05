
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.CotacaoService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.web.controller.form.CotacaoForm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class CotacaoController extends SGBController<Cotacao, CotacaoForm, CotacaoService> {

    @Autowired
    private CotacaoForm form;
    @Autowired
    private CotacaoService service;

    @Override
    public void initData() {
        this.getForm().setTabelaCotacoes(new ArrayList<Cotacao>());
        this.getForm().getTabelaCotacoes().addAll(this.getService().list());
    }
    
    @Override
    public CotacaoForm getForm() {
        return form;
    }

    @Override
    public CotacaoService getService() {
        return service;
    }

    public void exportarXLS(Cotacao cotacao) {
    }

    public void exportarCSV(Cotacao cotacao, boolean nacionais, boolean estrangeiros) {
    }
}
