package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG006;
import br.ufg.inf.es.model.Bibliografia;
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
 * Classe que define o Service da Disciplina
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DisciplinaService extends GenericService<Disciplina> {

    /** Campo dao*/
    @Autowired
    private DisciplinaDAO dao;
    
    /** Campo livroDao*/
    @Autowired
    private LivroDAO livroDao;
    
    /** Campo bibliografiaDAO*/
    @Autowired
    private BibliografiaDAO bibliografiaDAO;

    /**
     * Método que obtém o DAO da Bibliografia.
     *
     * @author User
     *
     * @return
     */
    public BibliografiaDAO getBibliografiaDAO() {
    	
        return bibliografiaDAO;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public DisciplinaDAO getDAO() {
    	
        return this.dao;
    }

    /**
     * Método que define o DAO da disciplina.
     *
     * @param dao
     */
    public void setDao(DisciplinaDAO dao) {

        this.dao = dao;
    }

    /**
     * Método que obtém o DAO do Livro.
     *
     * @return
     */
    public LivroDAO getLivroDao() {
    	
        return livroDao;
    }

    /**
     * Método que insere uma entidade no banco de dados.
     *
     * @param entidade
     * @throws ValidationException
     */
    @RNG006
    public void inserir(Disciplina entidade) throws ValidationException {

        Long id = this.getDAO().insert(entidade);

        Disciplina persistida = this.getDAO().find(id);

        if (UtilObjeto.isReferencia(entidade.getBibliografias()) && !entidade.getBibliografias().isEmpty()) {

            for (Bibliografia bl : entidade.getBibliografias()) {

                bl.setDisciplina(persistida);

                this.getBibliografiaDAO().update(bl);
            }
        }

    }

    /**
     * Método que lista as disciplinas para o autocomplete.
     *
     * @param query
     * @return Collection<Disciplina>
     */
    public Collection<Disciplina> complete(String query) {
        Collection<Disciplina> results = new ArrayList<Disciplina>();

        for (Disciplina disciplina : this.dao.list()) {
            if (disciplina.getNome().toUpperCase().contains(query.toUpperCase())) {
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

    /**
     * Método responsável por editar uma disciplina
     *
     * @author Cássio Augusto, Marco Aurélio
     */
    @RNG006
    public void editarDisciplina(Disciplina entidade) throws ValidationException {
        
        this.getDAO().update(entidade);
    }
}
