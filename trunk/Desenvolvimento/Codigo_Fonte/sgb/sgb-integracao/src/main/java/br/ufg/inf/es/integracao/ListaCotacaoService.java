
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.ListaCotacao;
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

    @Override
    public DAO<ListaCotacao, Long> getDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
