package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG006;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.BibliografiaDAO;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.ArrayList;
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
public class DisciplinaService extends GenericService<Disciplina> {

    @Autowired
    private DisciplinaDAO dao;
    @Autowired
    private LivroDAO livroDao;
    @Autowired
    private BibliografiaDAO bibliografiaDAO;

    public BibliografiaDAO getBibliografiaDAO() {
        return bibliografiaDAO;
    }

    @Override
    public DisciplinaDAO getDAO() {
        return this.dao;
    }

    public void setDao(DisciplinaDAO dao) {

        this.dao = dao;
    }

    public LivroDAO getLivroDao() {
        return livroDao;
    }

    @RNG006
    public void inserir(Disciplina entidade) throws ValidationException {

        Long id =  this.getDAO().insert(entidade);
        
        Disciplina persistida = this.getDAO().find(id);

        if (UtilObjeto.isReferencia(entidade.getBibliografias())) {

            for (Bibliografia bl : entidade.getBibliografias()) {

                    bl.setDisciplina(persistida);

                this.getBibliografiaDAO().update(bl);
            }

            persistida.setBibliografias(entidade.getBibliografias());
            
            this.getDAO().update(persistida);
        }

    }

    public Collection<Disciplina> complete(String query) {
        Collection<Disciplina> results = new ArrayList<Disciplina>();

        for (Disciplina disciplina : this.dao.list()) {
            if (disciplina.getNome().contains(query)) {
                results.add(disciplina);
            }
        }

        return results;
    }

    /**
     * Método responsável por buscar Livros de acordo com seu título
     *
     * @author Cássio Augusto Silva de Freitas
     * @param query
     */
    public Collection<Livro> buscaLivros(String query) {
        return this.getLivroDao().buscaLivroPorTitulo(query);
    }

    public void inserirDisciplina(Disciplina entity) throws ValidationException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
