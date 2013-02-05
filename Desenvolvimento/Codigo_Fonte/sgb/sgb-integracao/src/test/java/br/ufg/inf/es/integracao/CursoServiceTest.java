
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.aop.RNGExecutor;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.persistencia.AutorDAO;
import br.ufg.inf.es.persistencia.CursoDAO;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Classe responsável por realizar os testes de unidade na classe CursoService.
 * @author Allan Vieira Ribeiro
 */
public class CursoServiceTest {
    
    CursoDAO cursoDAO = new CursoDAO();
    
    CursoService cursoService = new CursoService();
    
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    
    Curso curso = new Curso();
    
    @Before
    public void setUp() { 
        
        ApplicationContext springContext = new FileSystemXmlApplicationContext("/src/main/resources/appContext-CursoServiceTest.xml");
        
        cursoService = springContext.getBean(CursoService.class);
        
        RNGExecutor rngExecutor = springContext.getBean(RNGExecutor.class);
        
        rngExecutor.setApplicationContext(springContext);
        
        cursoDAO = Mockito.mock(CursoDAO.class);
        
        disciplinaDAO = Mockito.mock(DisciplinaDAO.class);
        
        cursoService.setDao(cursoDAO);
        
        cursoService.setDisciplinaDao(disciplinaDAO);
        
        curso.setNome("Engenharia de Software");
        
        curso.setVagas(60);
    }
    
    @Test
    public void testInserirCurso001() throws ValidationException {
        
        Mockito.when(cursoDAO.insert(curso)).thenReturn(Long.MIN_VALUE);
        
        Long id = cursoService.insert(curso);
        
        Mockito.verify(cursoDAO).insert(curso);
                
        Assert.assertEquals(Long.MIN_VALUE, id.longValue());
    }
    
}
