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
 *
 * @author victor
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BibliografiaService extends GenericService<Bibliografia> {

    @Autowired
    private BibliografiaDAO dao;

    @Override
    public DAO<Bibliografia, Long> getDAO() {
        return this.dao;
    }

    public List<Bibliografia> findAllByLivro(Livro livro) {
        return dao.findAllByLivro(livro);
    }
}
