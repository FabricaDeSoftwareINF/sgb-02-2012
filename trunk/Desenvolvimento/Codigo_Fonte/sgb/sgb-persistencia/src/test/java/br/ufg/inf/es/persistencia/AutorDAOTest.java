package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * Testes para o DAO do autor
 *
 * @author Victor Carvalho, Cassio
 */
public class AutorDAOTest {

    private AutorDAO dao;
    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private List autores;

    @Before
    public void setUp() {
        dao = new AutorDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);


        dao.setSessionFactory(factory);

        AutorDTO joao = new AutorDTO();
        joao.setId(1L);
        joao.setNome("Joao");
        AutorDTO maria = new AutorDTO();
        maria.setId(2L);
        maria.setNome("Maria");
        autores = Arrays.asList(joao, maria);
    }

    /**
     * Test of getSessionFactory method, of class AutorDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of listarAutores method, of class AutorDAO.
     *
     */
    @Test
    public void testListarAutores() {
        when(factory.openSession()).thenReturn(session);
        when(session.createCriteria(eq(Autor.class), anyString())).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.setProjection(any(ProjectionList.class))).thenReturn(criteria);
        when(criteria.addOrder(any(Order.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(autores);
        when(criteria.setResultTransformer(any(ResultTransformer.class))).thenReturn(criteria);
        when(criteria.setResultTransformer(any(AliasToBeanResultTransformer.class))).thenReturn(criteria);

        String filtroNome = "filtro";
        Collection result = dao.listarAutores(filtroNome);
        assertEquals(autores, result);
    }
}
