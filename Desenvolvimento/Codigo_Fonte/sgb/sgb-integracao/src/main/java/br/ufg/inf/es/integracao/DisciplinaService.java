package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG006;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
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
    
    @Override
    @RNG006
    public Long insert(Disciplina entidade) throws ValidationException { 
        
        return this.getDAO().insert(entidade);
    }

    public Collection<Disciplina> complete(String query) {  
        Collection<Disciplina> results = new ArrayList<Disciplina>();
          
        for(Disciplina disciplina : this.dao.list()){
            if(disciplina.getNome().contains(query)){
                results.add(disciplina);
            }
        }
          
        return results;  
    } 
    
    /**
     * Método responsável por buscar Livros de acordo com seu título
     * @author Cássio Augusto Silva de Freitas
     * @param query 
     */
    public Collection<Livro> buscaLivros(String query) {
        return this.getLivroDao().buscaLivroPorTitulo(query);
    }

    public void inserirDisciplina(Disciplina entity) throws ValidationException{
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
