package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.BibliografiaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Service da bibliografia
 * 
 * @author victor
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BibliografiaService extends GenericService<Bibliografia> {

    /** Campo dao*/
    @Autowired
    private BibliografiaDAO dao;

    /**
     * obtem o dao da bibliografia
     * @return dao da bibliografia
     */
    @Override
    public DAO<Bibliografia, Long> getDAO() {
        return this.dao;
    }

    /**
     * Obtem todas as bibliografias vinculadas a um livro
     * @param livro base das bibliografias
     * @return lista de bibliografias que possuem o livro
     */
    public List<Bibliografia> findAllByLivro(Livro livro) {
        return dao.findAllByLivro(livro);
    }
}
