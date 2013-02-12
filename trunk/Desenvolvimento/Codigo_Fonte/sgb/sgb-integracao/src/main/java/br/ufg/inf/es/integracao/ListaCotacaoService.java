
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe Service para a entidade ListaCotacao
 * @author Bruno Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaCotacaoService extends GenericService<ListaCotacao> {

    /** Campo dao*/
    @Autowired
    private ListaCotacaoDAO dao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public DAO<ListaCotacao, Long> getDAO() {
    	
        return dao;
    }

    /**
     * Método que define o DAO da ListaCotacao
     *
     * @author User
     *
     * @param dao
     */
    public void setDao(ListaCotacaoDAO dao) {
    	
        this.dao = dao;
    }
    
}
