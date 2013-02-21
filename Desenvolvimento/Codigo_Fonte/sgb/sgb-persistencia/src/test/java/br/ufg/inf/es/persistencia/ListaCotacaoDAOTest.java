package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ListaCotacao;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author victor
 */
public class ListaCotacaoDAOTest {

    private ListaCotacaoDAO dao;
    private SessionFactory factory;
    private Session session;
    private List cotacoes;

    @Before
    public void setUp() {
        dao = new ListaCotacaoDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);

        dao.setSessionFactory(factory);

        cotacoes = Arrays.asList(new ListaCotacao());
    }

    /**
     * Test of getSessionFactory method, of class ListaCotacaoDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of list method, of class ListaCotacaoDAO.
     */
    @Test
    public void testList() {
        Query query = mock(Query.class);
        when(factory.openSession()).thenReturn(session);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(cotacoes);

        Collection result = dao.list();
        assertEquals(cotacoes, result);
    }
}
