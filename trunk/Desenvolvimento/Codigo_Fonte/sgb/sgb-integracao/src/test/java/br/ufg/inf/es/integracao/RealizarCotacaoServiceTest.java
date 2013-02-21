package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Marco Aurélio Camargo Oliveira
 */
public class RealizarCotacaoServiceTest {

    private ListaCotacaoDAO dao;
    private RealizarCotacaoService service;
    private LivroDAO livroDao;
    private CotacaoDAO cotacaoDao;
    private LivrosBibliotecaDAO bibliotecaDao;
    private ParametrosDAO parametrosDao;

    @Before
    public void setUp() {
        service = new RealizarCotacaoService();
        dao = Mockito.mock(ListaCotacaoDAO.class);
        livroDao = Mockito.mock(LivroDAO.class);
        cotacaoDao = Mockito.mock(CotacaoDAO.class);
        bibliotecaDao = Mockito.mock(LivrosBibliotecaDAO.class);
        parametrosDao = Mockito.mock(ParametrosDAO.class);

        service.setDao(dao);
        service.setLivroDao(livroDao);
        service.setBibliotecaDao(bibliotecaDao);
        service.setCotacaoDao(cotacaoDao);
        service.setParametrosDao(parametrosDao);
    }

    /**
     * Test of getDAO method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetDAO() {
        ListaCotacaoDAO result = service.getDAO();
        assertEquals(dao, result);
    }

    /**
     * Test of setDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testSetDao() {
        ListaCotacaoDAO novoDao = new ListaCotacaoDAO();
        service.setDao(novoDao);
        ListaCotacaoDAO result = service.getDAO();
        assertEquals(novoDao, result);
    }

    /**
     * Test of getDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetDao() {
        ListaCotacaoDAO result = service.getDao();
        assertEquals(dao, result);
    }

    /**
     * Test of getLivroDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetLivroDao() {
        LivroDAO result = service.getLivroDao();
        assertEquals(livroDao, result);
    }

    /**
     * Test of getCotacaoDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetCotacaoDao() {
        CotacaoDAO result = service.getCotacaoDao();
        assertEquals(cotacaoDao, result);
    }

    /**
     * Test of realizarCotacao method, of class RealizarCotacaoService.
     */
    @Test
    public void testRealizarCotacao() {
        Collection<Livro> livros = null;
        ListaCotacao expResult = null;
        ListaCotacao result = service.realizarCotacao(livros);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBibliotecaDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetBibliotecaDao() {
        LivrosBibliotecaDAO result = service.getBibliotecaDao();
        assertEquals(bibliotecaDao, result);
    }

    /**
     * Test of getParametrosDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetParametrosDao() {
        ParametrosDAO result = service.getParametrosDao();
        assertEquals(parametrosDao, result);
    }

    /**
     * Test of salvarListaCotacao method, of class RealizarCotacaoService.
     */
    @Test
    public void testSalvarListaCotacao() throws Exception {
        Long idEsperado = 1L;
        ListaCotacao listaCotacao = new ListaCotacao();

        Mockito.when(dao.insert(listaCotacao)).thenReturn(idEsperado);   
        Long result = service.insert(listaCotacao);
        
        assertEquals(idEsperado, result);
    }
}
