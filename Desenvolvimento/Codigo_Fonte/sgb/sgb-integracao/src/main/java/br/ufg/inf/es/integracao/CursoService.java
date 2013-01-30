package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG004;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.persistencia.CursoDAO;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por executar e delegar as funções relativas ao Curso.
 *
 * @author Diogo Gon&ccedil;alves Teodoro
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CursoService extends GenericService<Curso> {

    @Autowired
    private CursoDAO dao;
    
    @Autowired
    private DisciplinaDAO disciplinaDao;

    /**
     * Método responsável pela inserção de um <code>Curso</code>
     * @param entity
     * @return Id do curso salvo
     * @throws ValidationException 
     */
    @Override
    @RNG004
    public Long insert(final Curso entity) throws ValidationException {

        return super.insert(entity);
    }
    
    /**
     * Método responsável pela inserção de um <code>Curso</code>
     * 
     * @author Allan Vieira Ribeiro
     * 
     * @param curso
     * @return Id do curso salvo
     * @throws ValidationException 
     */
    @RNG004
    public Long insert(final Curso curso, final Collection<Disciplina> disciplinas) throws ValidationException {

        long id = this.getDAO().insert(curso);

        Curso cursoPersistido = this.getDAO().find(id);

        for(Disciplina disc : disciplinas)  {

            disc.setCurso(cursoPersistido);

            curso.getDisciplinas().add(disc);

            this.getDisciplinaDao().update(disc);
        }      

        this.getDAO().update(cursoPersistido);            
        
        return id;
    }
    
    /**
     * Método responsável por listar as disciplinas cadastradas que ainda não foram
     * vinculadas a nenhum curso.
     * @return Coleção com a disciplinas.
     */
    public Collection<Disciplina> listarDisciplinasNaoVinculadasACurso(){
        
        return this.getDisciplinaDao().listarDisciplinasNaoVinculadasACurso();
    }
    
    @Override
    public CursoDAO getDAO() {

        return this.dao;
    }

    public DisciplinaDAO getDisciplinaDao() {
     
        return this.disciplinaDao;
    } 
}
