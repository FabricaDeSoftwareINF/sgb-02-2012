package br.ufg.inf.es.integracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.persistencia.BibliografiaDAO;

/**
 * Service da bibliografia
 *
 * @author victor
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BibliografiaService extends GenericService<Bibliografia> {

    /**
     * Campo dao
     */
    @Autowired
    private BibliografiaDAO dao;

    /**
     * obtem o dao da bibliografia
     *
     * @return dao da bibliografia
     */
    @Override
    public BibliografiaDAO getDAO() {
        return this.dao;
    }
}
