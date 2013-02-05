
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaCotacaoService extends GenericService<ListaCotacao> {

    @Autowired
    private ListaCotacaoDAO dao;
    
    @Override
    public DAO<ListaCotacao, Long> getDAO() {
        return dao;
    }

    public void setDao(ListaCotacaoDAO dao) {
        this.dao = dao;
    }
    
}
