package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author alunoufg, victor
 */
public class PerfilDAOTest {

    @Test
    public void testGetSessionFactory() {
        SessionFactory sessionF = Mockito.mock(SessionFactory.class);
        PerfilDAO instance = Mockito.mock(PerfilDAO.class);
        Mockito.when(instance.getSessionFactory()).thenReturn(sessionF);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionF, result);
    }

    @Test
    public void testSetSessionFactory() {
        SessionFactory sessionF = Mockito.mock(SessionFactory.class);
        PerfilDAO instance = new PerfilDAO();
        instance.setSessionFactory(sessionF);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionF, result);
    }

    @Test
    public void testGetSessionFactory2() {

        PerfilDAO instance = new PerfilDAO();

        SessionFactory result = instance.getSessionFactory();
        assertEquals(null, result);

    }
}
