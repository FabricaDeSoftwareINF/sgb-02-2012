package br.ufg.inf.es.integracao;

import org.mockito.Mockito;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Aurélio Camargo Oliveira
 */
public class ListaComprasServiceTest {

    private ListaComprasDAO dao;
    private LivroDAO livroDao;
    private UsuarioDAO usuarioDao;
    private ListaComprasService service;

    @Before
    public void setUp() {
        service = new ListaComprasService();
        dao = Mockito.mock(ListaComprasDAO.class);
        livroDao = Mockito.mock(LivroDAO.class);
        usuarioDao = Mockito.mock(UsuarioDAO.class);

        service.setDao(dao);
        service.setUsuarioDao(usuarioDao);
        service.setLivroDao(livroDao);
    }

    /**
     * Test of getLivroDao method, of class ListaComprasService.
     */
    @Test
    public void testGetLivroDao() {
        LivroDAO result = service.getLivroDao();
        assertEquals(livroDao, result);
    }

    /**
     * Test of getDAO method, of class ListaComprasService.
     */
    @Test
    public void testGetDAO() {
        ListaComprasDAO result = service.getDAO();
        assertEquals(dao, result);
    }

    /**
     * Test of setDao method, of class ListaComprasService.
     */
    @Test
    public void testSetDao() {
        ListaComprasDAO novoDao = new ListaComprasDAO();
        service.setDao(novoDao);
        ListaComprasDAO result = service.getDAO();
        assertEquals(novoDao, result);
    }

    /**
     * Test of carregarLivrosDaListaCompras method, of class ListaComprasService.
     */
    @Test
    public void testCarregarLivrosDaListaCompras() {
        System.out.println("carregarLivrosDaListaCompras");
        Collection<ListaCompras> listaCompras = null;
        ListaComprasService instance = new ListaComprasService();
        instance.carregarLivrosDaListaCompras(listaCompras);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscaTodosLivros method, of class ListaComprasService.
     */
    @Test
    public void testBuscaTodosLivros() {
        System.out.println("buscaTodosLivros");
        String filtroTitulo = "";
        ListaComprasService instance = new ListaComprasService();
        Collection expResult = null;
        Collection result = instance.buscaTodosLivros(filtroTitulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioDao method, of class ListaComprasService.
     */
    @Test
    public void testGetUsuarioDao() {
        UsuarioDAO result = service.getUsuarioDao();
        assertEquals(usuarioDao, result);
    }

    /**
     * Test of setUsuarioDao method, of class ListaComprasService.
     */
    @Test
    public void testSetUsuarioDao() {
        UsuarioDAO novoDao = new UsuarioDAO();
        service.setUsuarioDao(usuarioDao);
        UsuarioDAO result = service.getUsuarioDao();
        assertEquals(novoDao, result);
    }

    /**
     * Test of criaListaCompras method, of class ListaComprasService.
     */
    @Test
    public void testCriaListaCompras() {
        
    }
}
