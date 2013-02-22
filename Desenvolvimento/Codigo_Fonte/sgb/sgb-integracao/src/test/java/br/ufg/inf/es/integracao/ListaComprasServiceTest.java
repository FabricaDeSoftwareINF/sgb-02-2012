package br.ufg.inf.es.integracao;

import org.mockito.Mockito;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.ItemListaComprasDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Aurélio Camargo Oliveira
 */
public class ListaComprasServiceTest {

    private ListaComprasDAO dao;
    private ItemListaComprasDAO livroDao;
    private UsuarioDAO usuarioDao;
    private ListaComprasService service;

    @Before
    public void setUp() {
        service = new ListaComprasService();
        dao = Mockito.mock(ListaComprasDAO.class);
        livroDao = Mockito.mock(ItemListaComprasDAO.class);
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
        ItemListaComprasDAO result = service.getLivroDao();
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
        
    }

    /**
     * Test of buscaTodosLivros method, of class ListaComprasService.
     */
    @Test
    public void testBuscaTodosLivros() {
        
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
       
        service.setUsuarioDao(usuarioDao);
        UsuarioDAO result = service.getUsuarioDao();
        assertEquals(usuarioDao, result);
    }

    /**
     * Test of criaListaCompras method, of class ListaComprasService.
     */
    @Test
    public void testCriaListaCompras() {
        
    }
}
