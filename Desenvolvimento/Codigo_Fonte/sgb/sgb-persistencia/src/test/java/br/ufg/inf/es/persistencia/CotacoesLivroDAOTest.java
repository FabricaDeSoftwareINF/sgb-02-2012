package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Victor Carvalho
 */
public class CotacoesLivroDAOTest {

    private CotacoesLivroDAO dao;
    private SessionFactory factory;

    @Before
    public void setUp() {
        dao = new CotacoesLivroDAO();
        this.factory = mock(SessionFactory.class);

        dao.setSessionFactory(factory);
    }

    /**
     * Test of comstructor
     */
    @Test
    public void testCriarNovoDaoComSerssionFactory() {
        CotacoesLivroDAO novoDao = new CotacoesLivroDAO(factory);
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
