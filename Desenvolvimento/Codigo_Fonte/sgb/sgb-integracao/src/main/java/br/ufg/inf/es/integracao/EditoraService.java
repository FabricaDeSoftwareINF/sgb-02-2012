package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG007;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.persistencia.EditoraDAO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Henrique Hirako
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EditoraService extends GenericService<Editora> {
    
     public static final String KEY_RNG007 = "label.RNG07.nome";

    @Autowired
    private EditoraDAO dao;

    @Override
    public EditoraDAO getDAO() {

        return this.dao;
    }

    public void setDao(EditoraDAO dao) {

        this.dao = dao;
    }

    public Collection<Editora> complete(String query) {
        Collection<Editora> results = new ArrayList<Editora>();

        for (Editora editora : dao.list()) {
            if (editora.getNome().contains(query)) {
                results.add(editora);
            }
        }

        return results;
    }

    /**
     * 
     * @param entidade
     * @return
     * @throws ValidationException 
     */
    @Override
    @RNG007
    public Long insert(Editora entidade) throws ValidationException {

        return this.getDAO().insert(entidade);

    }

    /**
     * 
     * @param entidade
     * @throws ValidationException 
     */
    @Override
    public void save(Editora entidade) throws ValidationException {
      
        Editora editora = this.getDAO().find(entidade.getId());
      
        editora.setNome(entidade.getNome());
        
        this.getDAO().save(editora);

    }
    
}