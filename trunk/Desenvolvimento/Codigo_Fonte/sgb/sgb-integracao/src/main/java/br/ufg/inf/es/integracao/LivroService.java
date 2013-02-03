package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG000;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LivroService extends GenericService<Livro> {

    @Autowired
    private LivroDAO dao;

    @Override
    public LivroDAO getDAO() {
        return this.dao;
    }

    public void setDao(LivroDAO dao) {

        this.dao = dao;
    }

    @Override
    @RNG000
    public Long insert(Livro entity) throws ValidationException {

        return super.insert(entity);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Livro> list() {
        
        Collection<Livro> data = super.list();
        
        for (Livro livro : data) {
      
            livro.setBibliografias((Collection<Bibliografia>) this.getDAO().getBibliografia(livro.getId()));
       
            livro.setAutores((Collection<Autor>) this.getDAO().getAutores(livro.getId()));
      
        }
        return super.list();
    }
}