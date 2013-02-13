package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Alunoinf_2
 */
public class LivroServiceTest {

    /**
     * Test of getDAO method, of class LivroService.
     */
    @Test
    public void testGetDAO() {

        LivroService instance = new LivroService();
        LivroDAO expResult = new LivroDAO();
        instance.setDao(expResult);
        LivroDAO result = instance.getDAO();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDao method, of class LivroService.
     */
    @Test
    public void testSetDao() {
        LivroDAO dao = new LivroDAO();
        LivroService instance = new LivroService();
        instance.setDao(dao);
    }

    /**
     * Test of insert method, of class LivroService.
     */
    @Test
    public void testInsert() throws Exception {

        Livro entity = new Livro();
        LivroService instance = new LivroService();
        LivroDAO livroDao = Mockito.mock(LivroDAO.class);
        Mockito.when(livroDao.insert(entity)).thenReturn(0L);
        instance.setDao(livroDao);
        Long expResult = 0L;
        Long result = instance.insert(entity);
        assertEquals(expResult, result);
    }

    @Test
    public void testMessageToProperty() {
        LivroService livroService = new LivroService();
        String hibernateMsg = "Duplicate entry '' for 'isbn10'";
        String msg = livroService.messageToProperty(hibernateMsg);
        assertEquals("duplicate.isbn10", msg);
    }
}
