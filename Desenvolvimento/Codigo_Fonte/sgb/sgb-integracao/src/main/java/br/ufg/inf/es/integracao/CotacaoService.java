/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CotacaoDAO dao;
    
    @Override
    public DAO<Cotacao, Long> getDAO() {
        return dao;
    }

    public void setDao(CotacaoDAO dao) {
        this.dao = dao;
    }
   
    
}
