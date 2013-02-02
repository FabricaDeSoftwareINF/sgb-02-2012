/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Cotacao;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marquete
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CotacaoService extends GenericService<Cotacao> {

    @Override
    public DAO<Cotacao, Long> getDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
