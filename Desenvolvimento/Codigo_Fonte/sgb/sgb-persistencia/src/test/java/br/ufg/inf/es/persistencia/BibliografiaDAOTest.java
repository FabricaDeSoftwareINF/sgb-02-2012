package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Victor Carvalho
 */
public class BibliografiaDAOTest {

    private BibliografiaDAO dao;
    private SessionFactory factory;

    @Before
    public void setUp() {
        dao = new BibliografiaDAO();
        this.factory = mock(SessionFactory.class);

        dao.setSessionFactory(factory);
    }

    /**
     * Test of getSessionFactory method, of class BibliografiaDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }
}