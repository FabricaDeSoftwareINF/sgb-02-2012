/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.CotacaoService;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.web.controller.form.CotacaoForm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class CotacaoController extends SGBController<Cotacao, CotacaoForm, CotacaoService>{

    @Override
    public CotacaoForm getForm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CotacaoService getService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
