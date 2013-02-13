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
 * Classe servidora para entidade Cotacao
 * @author Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CotacaoService extends GenericService<Cotacao> {

    /** Campo dao*/
    @Autowired
    private CotacaoDAO dao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public DAO<Cotacao, Long> getDAO() {
        return dao;
    }

    /**
     * Método que define o DAO da Cotacao.
     *
     * @param dao
     */
    public void setDao(CotacaoDAO dao) {
    	
        this.dao = dao;
    }   
    
}
