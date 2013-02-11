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
 * Service da entidade enditora
 *
 * @author Henrique Hirako
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EditoraService extends GenericService<Editora> {

    //mensagem da regra de negocio
    public static final String KEY_RNG007 = "label.RNG07.nome";
    @Autowired
    private EditoraDAO dao;

    /**
     * obtem o dao da editora
     *
     * @return
     */
    @Override
    public EditoraDAO getDAO() {
        return this.dao;
    }

    /**
     * Define o dao da editora
     *
     * @param dao
     */
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
     * Insere uma nova editora validando-a antes
     *
     * @param entidade
     * @return id da editora inserida
     * @throws ValidationException
     */
    @Override
    @RNG007
    public Long insert(Editora entidade) throws ValidationException {

        return this.getDAO().insert(entidade);

    }

    /**
     * Edita uma editora existente
     *
     * @param entidade
     * @throws ValidationException
     */
    @RNG007
    public void editar(Editora entidade) throws ValidationException {

        this.getDAO().update(entidade);

    }
}