package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author victor
 */
public class EditoraDAOTest {

    private EditoraDAO dao;
    private SessionFactory factory;

    @Before
    public void setUp() {
        dao = new EditoraDAO();
        this.factory = mock(SessionFactory.class);

        dao.setSessionFactory(factory);
    }

    /**
     * Test of getSessionFactory method, of class CotacaoDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }
}
