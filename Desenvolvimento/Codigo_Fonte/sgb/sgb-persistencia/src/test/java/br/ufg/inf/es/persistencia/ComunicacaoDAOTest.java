package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.model.Comunicacao;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Victor Carvalho
 */
public class ComunicacaoDAOTest {

    private ComunicacaoDAO dao;
    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Comunicacao comunicacao;

    @Before
    public void setUp() {
        dao = new ComunicacaoDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);

        dao.setSessionFactory(factory);

        comunicacao = new Comunicacao();
    }

    /**
     * Test of getSessionFactory method, of class ComunicacaoDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of getComunicacao method, of class ComunicacaoDAO.
     */
    @Test
    public void testGetComunicacao() {
        when(factory.openSession()).thenReturn(session);
        when(session.createCriteria(eq(Comunicacao.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(comunicacao);

        Comunicacao result = dao.getComunicacao();
        assertEquals(comunicacao, result);
    }
}
