package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.BibliografiaDAO;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * Testes para o servico de disciplina
 * 
 * @author Victor Carvalho
 */
public class DisciplinaServiceTest {

    private DisciplinaService service;
    private DisciplinaDAO dao;
    private BibliografiaDAO bibliografiaDAO;
    private LivroDAO livroDAO;

    @Before
    public void setUp() {
        service = new DisciplinaService();
        dao = Mockito.mock(DisciplinaDAO.class);
        bibliografiaDAO = Mockito.mock(BibliografiaDAO.class);
        livroDAO = Mockito.mock(LivroDAO.class);

        service.setDao(dao);
        service.setBibliografiaDAO(bibliografiaDAO);
        service.setLivroDao(livroDAO);
    }

    /**
     * Test of getBibliografiaDAO method, of class DisciplinaService.
     */
    @Test
    public void testGetBibliografiaDAO() {
        BibliografiaDAO result = service.getBibliografiaDAO();
        assertEquals(bibliografiaDAO, result);
    }

    /**
     * Test of setBibliografiaDAO method, of class DisciplinaService.
     */
    @Test
    public void testSetBibliografiaDAO() {
        BibliografiaDAO novoDao = new BibliografiaDAO();
        service.setBibliografiaDAO(novoDao);
        BibliografiaDAO result = service.getBibliografiaDAO();
        assertEquals(novoDao, result);
    }

    /**
     * Test of getDAO method, of class DisciplinaService.
     */
    @Test
    public void testGetDAO() {
        DisciplinaDAO result = service.getDAO();
        assertEquals(dao, result);
    }

    /**
     * Test of setDao method, of class DisciplinaService.
     */
    @Test
    public void testSetDao() {
        DisciplinaDAO novoDao = new DisciplinaDAO();
        service.setDao(novoDao);
        DisciplinaDAO result = service.getDAO();
        assertEquals(novoDao, result);
    }

    /**
     * Test of getLivroDao method, of class DisciplinaService.
     */
    @Test
    public void testGetLivroDao() {
        LivroDAO result = service.getLivroDao();
        assertEquals(livroDAO, result);
    }

    /**
     * Test of setLivroDao method, of class DisciplinaService.
     */
    @Test
    public void testSetLivroDao() {
        LivroDAO novoDAO = new LivroDAO();
        service.setLivroDao(novoDAO);
        LivroDAO result = service.getLivroDao();
        assertEquals(novoDAO, result);
    }

    /**
     * Test of inserir method when don't has bibliografias, of class
     * DisciplinaService.
     */
    @Test
    public void testInserirDisciplinaSemBibliografias() throws Exception {
        Long idEsperado = 1L;
        Disciplina disciplina = new Disciplina();

        Mockito.when(dao.insert(disciplina)).thenReturn(idEsperado);
        Long result = service.inserir(disciplina);

        assertEquals(idEsperado, result);
    }

    /**
     * Test of inserir method when has a list of bibliografias, of class
     * DisciplinaService.
     *
     */
    @Test
    public void testInserirDisciplinaComBibliografias() throws Exception {
        Long idEsperado = 1L;
        Bibliografia bibliografia = new Bibliografia();
        Disciplina disciplina = new Disciplina();
        disciplina.setBibliografias(Arrays.asList(bibliografia));

        Mockito.when(dao.insert(disciplina)).thenReturn(idEsperado);
        Long result = service.inserir(disciplina);

        assertEquals(idEsperado, result);
    }

    /**
     * Test of inserir method when has a empty list of bibliografias, of class
     * DisciplinaService.
     */
    @Test
    public void testInserirDisciplinaComListaDeBibliografiasVazia() throws Exception {
        Long idEsperado = 1L;
        Disciplina disciplina = new Disciplina();
        disciplina.setBibliografias(new ArrayList<Bibliografia>());

        Mockito.when(dao.insert(disciplina)).thenReturn(idEsperado);
        Long result = service.inserir(disciplina);

        assertEquals(idEsperado, result);
    }

    /**
     * Test of complete method, of class DisciplinaService.
     */
    @Test
    public void testComplete() {
        String query = "INTEGRACAO";
        Disciplina disciplinaInvalida = new Disciplina();
        disciplinaInvalida.setNome("nome diferente da query");
        Disciplina disciplinaValida = new Disciplina();
        disciplinaValida.setNome(query);

        Mockito.when(dao.list()).thenReturn(Arrays.asList(disciplinaValida, disciplinaInvalida));

        Collection result = service.complete(query);
        assertEquals("Deveria ter sido retornada uma discplina com o nome definido na query",
                1, result.size());
    }

    /**
     * Test of buscaLivros method, of class DisciplinaService.
     */
    @Test
    public void testBuscaLivros() {
        String query = "query";
        Livro livro = new Livro();
        Mockito.when(livroDAO.buscaLivroPorTitulo(query)).thenReturn(Arrays.asList(livro));

        Collection result = service.buscaLivros(query);
        assertEquals("Deveria ter sido retornado um livro com a query informada",
                1, result.size());
    }

    /**
     * Test of editarDisciplina method, of class DisciplinaService.
     */
    @Test
    public void testEditarDisciplina() throws Exception {
        service = Mockito.mock(DisciplinaService.class);
        service.update(new Disciplina());
    }
}