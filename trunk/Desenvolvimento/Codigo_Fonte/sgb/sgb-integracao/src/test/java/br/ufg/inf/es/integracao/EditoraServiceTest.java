package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.persistencia.EditoraDAO;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * Testes do service da entidade Editora
 * 
 * @author Victor Carvalho
 */
public class EditoraServiceTest {

    private EditoraDAO editoraDAO;
    private EditoraService editoraService;

    @Before
    public void setUp() {
        editoraDAO = Mockito.mock(EditoraDAO.class);
        editoraService = new EditoraService();

        editoraService.setDao(editoraDAO);
    }

    /**
     * Test of getDAO method, of class EditoraService.
     */
    @Test
    public void testGetDAO() {
        EditoraDAO result = editoraService.getDAO();
        assertEquals(editoraDAO, result);
    }

    /**
     * Test of setDAO method, of class EditoraService.
     */
    @Test
    public void testSetDAO() {
        EditoraDAO novoDao = new EditoraDAO();
        editoraService.setDao(novoDao);
        assertEquals(novoDao, editoraService.getDAO());
    }

    /**
     * Test of complete method, of class EditoraService.
     */
    @Test
    public void testComplete() {
        String query = "UFG";
        Editora editora1 = new Editora();
        editora1.setNome("nome diferente da query");
        Editora editora2 = new Editora();
        editora2.setNome(query);

        Mockito.when(editoraDAO.list()).thenReturn(Arrays.asList(editora1, editora2));

        Collection result = editoraService.complete(query);
        assertEquals("Deveria ter retornado apenas uma editora", 1, result.size());
    }

    /**
     * Test of insert method, of class EditoraService.
     */
    @Test
    public void testInsert() throws Exception {
        Long idEsperado = 1L;
        Editora entidade = new Editora();
        Mockito.when(editoraDAO.insert(entidade)).thenReturn(idEsperado);
        Long result = editoraService.insert(entidade);
        assertEquals(idEsperado, result);
    }

    /**
     * Test of editar method, of class EditoraService.
     */
    @Test
    public void testEditar() throws Exception {
        Editora entidade = new Editora();
        editoraService.update(entidade);
    }
}