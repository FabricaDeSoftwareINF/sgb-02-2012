package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Alunoinf_2
 */
public class LivroDAOTest {

    /**
     * Test of getSessionFactory method, of class LivroDAO.
     */
    @Test
    public void testGetSessionFactory() {
       
        LivroDAO instance = new LivroDAO();
        SessionFactory expResult = Mockito.mock(SessionFactory.class);
        instance.setSessionFactory(expResult);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(expResult, result);
       
    }
}
