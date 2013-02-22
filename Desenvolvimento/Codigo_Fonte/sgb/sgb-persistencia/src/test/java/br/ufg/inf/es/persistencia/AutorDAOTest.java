/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.transform.ResultTransformer;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Alunoinf_2
 */
public class AutorDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private AutorDAO dao;

    public AutorDAOTest() {

        dao = new AutorDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        dao.setSessionFactory(factory);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSessionFactory method, of class AutorDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of setSessionFactory method, of class AutorDAO.
     */
    @Test
    public void testSetSessionFactory() {

        this.dao.setSessionFactory(this.factory);

        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of listarAutores method, of class AutorDAO.
     */
    @Test
    public void testListarAutores() {
        String filtroNome = "";
        AutorDAO instance = new AutorDAO();

        instance.setSessionFactory(factory);

        AutorDTO autor1 = new AutorDTO();
        autor1.setId(1L);
        autor1.setNome("Cássio");
        AutorDTO autor2 = new AutorDTO();
        autor1.setId(2L);
        autor2.setNome("Cássio 2");

        List<AutorDTO> autores = new ArrayList<AutorDTO>();
        autores.add(autor2);
        autores.add(autor1);

        when(factory.openSession()).thenReturn(session);
        when(session.createCriteria(eq(Autor.class), anyString())).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.setProjection(any(ProjectionList.class))).thenReturn(criteria);
        when(criteria.setResultTransformer(any(ResultTransformer.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(autores);

        Collection<AutorDTO> expResult = autores;

        Collection result = instance.listarAutores(filtroNome);
        assertEquals(expResult, result);

    }
    
     /**
     * Test of listarAutores method, of class AutorDAO.
     */
    @Test
    public void testListarAutores2() {
        String filtroNome = "filtro";
        AutorDAO instance = new AutorDAO();

        instance.setSessionFactory(factory);

        AutorDTO autor1 = new AutorDTO();
        autor1.setId(1L);
        autor1.setNome("Cássio");
        AutorDTO autor2 = new AutorDTO();
        autor1.setId(2L);
        autor2.setNome("Cássio 2");

        List<AutorDTO> autores = new ArrayList<AutorDTO>();
        autores.add(autor2);
        autores.add(autor1);

        when(factory.openSession()).thenReturn(session);
        when(session.createCriteria(eq(Autor.class), anyString())).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.setProjection(any(ProjectionList.class))).thenReturn(criteria);
        when(criteria.setResultTransformer(any(ResultTransformer.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(autores);

        Collection<AutorDTO> expResult = autores;

        Collection result = instance.listarAutores(filtroNome);
        assertEquals(expResult, result);

    }
}
