
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.ListaCotacaoService;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.web.controller.form.ListaCotacaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class ListaCotacaoController extends SGBController<ListaCotacao, ListaCotacaoForm, ListaCotacaoService> {

    @Autowired
    private ListaCotacaoForm form;
    @Autowired
    private ListaCotacaoService service;

    @Override
    public ListaCotacaoForm getForm() {
        return form;
    }

    @Override
    public ListaCotacaoService getService() {
        return service;
    }

    public void exportarXLS(ListaCotacao cotacao) {
    }

    public void exportarCSV(ListaCotacao cotacao, boolean nacionais, boolean estrangeiros) {
    }
}
