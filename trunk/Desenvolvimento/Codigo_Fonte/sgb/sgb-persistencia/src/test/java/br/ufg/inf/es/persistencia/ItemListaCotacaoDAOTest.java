package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Victor Carvalho
 */
public class ItemListaCotacaoDAOTest {

    private ItemListaCotacaoDAO dao;
    private SessionFactory factory;

    @Before
    public void setUp() {
        dao = new ItemListaCotacaoDAO();
        this.factory = mock(SessionFactory.class);

        dao.setSessionFactory(factory);
    }

    /**
     * Test of comstructor
     */
    @Test
    public void testCriarNovoDaoComSerssionFactory() {
        ItemListaCotacaoDAO novoDao = new ItemListaCotacaoDAO(factory);
        assertEquals(factory, novoDao.getSessionFactory());
    }

    /**
     * Test of getSessionFactory method, of class CotacaoDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }
}
