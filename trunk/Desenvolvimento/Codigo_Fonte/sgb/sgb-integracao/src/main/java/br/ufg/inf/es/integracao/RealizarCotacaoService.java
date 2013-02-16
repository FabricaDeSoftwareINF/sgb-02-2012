package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RealizarCotacaoService extends GenericService<ListaCotacao> {

    /** Campo dao*/
    @Autowired
    private ListaCotacaoDAO dao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public ListaCotacaoDAO getDAO() {
        return this.dao;
    }

    /**
     * Método que define o DAO do Livro.
     *
     * @param dao
     */
    public void setDao(ListaCotacaoDAO dao) {

        this.dao = dao;
    }

}