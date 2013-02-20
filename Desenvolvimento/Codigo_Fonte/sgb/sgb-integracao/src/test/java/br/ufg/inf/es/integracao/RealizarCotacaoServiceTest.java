package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @Before
    public void setUp() {
        service = new RealizarCotacaoService();
        dao = Mockito.mock(ListaCotacaoDAO.class);

        service.setDao(dao);
    }

    /**
     * Test of getDAO method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetDAO() {
        RealizarCotacaoService instance = new RealizarCotacaoService();

        ListaCotacaoDAO result = instance.getDAO();
        assertNull(result);
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
        System.out.println("getDao");
        RealizarCotacaoService instance = new RealizarCotacaoService();
        ListaCotacaoDAO expResult = null;
        ListaCotacaoDAO result = instance.getDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLivroDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetLivroDao() {
        System.out.println("getLivroDao");
        RealizarCotacaoService instance = new RealizarCotacaoService();
        LivroDAO expResult = null;
        LivroDAO result = instance.getLivroDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCotacaoDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetCotacaoDao() {
        System.out.println("getCotacaoDao");
        RealizarCotacaoService instance = new RealizarCotacaoService();
        CotacaoDAO expResult = null;
        CotacaoDAO result = instance.getCotacaoDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of realizarCotacao method, of class RealizarCotacaoService.
     */
    @Test
    public void testRealizarCotacao() {
        System.out.println("realizarCotacao");
        Collection<Livro> livros = null;
        RealizarCotacaoService instance = new RealizarCotacaoService();
        ListaCotacao expResult = null;
        ListaCotacao result = instance.realizarCotacao(livros);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBibliotecaDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetBibliotecaDao() {
        System.out.println("getBibliotecaDao");
        RealizarCotacaoService instance = new RealizarCotacaoService();
        LivrosBibliotecaDAO expResult = null;
        LivrosBibliotecaDAO result = instance.getBibliotecaDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParametrosDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetParametrosDao() {
        System.out.println("getParametrosDao");
        RealizarCotacaoService instance = new RealizarCotacaoService();
        ParametrosDAO expResult = null;
        ParametrosDAO result = instance.getParametrosDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarListaCotacao method, of class RealizarCotacaoService.
     */
    @Test
    public void testSalvarListaCotacao() {
        System.out.println("salvarListaCotacao");
        Collection<CotacoesLivro> cotacoesSelecionadas = null;
        String nome = "";
        RealizarCotacaoService instance = new RealizarCotacaoService();
        instance.salvarListaCotacao(cotacoesSelecionadas, nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
