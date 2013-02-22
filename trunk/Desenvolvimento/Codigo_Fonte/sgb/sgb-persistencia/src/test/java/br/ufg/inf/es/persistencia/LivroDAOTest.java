/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Livro;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;

/**
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class LivroDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Connection connection;
    private LivroDAO dao;
    private LivroDAO spyDAO;

    @Before
    public void setUp() {

        dao = new LivroDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.connection = mock(Connection.class);
        dao.setSessionFactory(factory);
        spyDAO = spy(new LivroDAO());
        spyDAO.setSessionFactory(factory);
    }

    /**
     * Método responsável por definir o retorno do Método openSesison() do mock
     * SessionFactory
     */
    public void preparaSessionFactoryMock() {
        when((factory.openSession())).thenReturn(session);
    }

    /**
     * Test of setSessionFactory method, of class DisciplinaDAO.
     */
    @Test
    public void testSetSessionFactory() {

        this.dao.setSessionFactory(this.factory);

        assertEquals(factory, dao.getSessionFactory());

    }

    /**
     * Test of getSessionFactory method, of class LivroDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of getAutores method, of class LivroDAO.
     */
    @Test
    public void testGetAutOores() {

        Collection autores = Arrays.asList(new Autor());

        when(spyDAO.getCollection(anyLong(), anyString())).thenReturn(autores);
        
        Collection<?> result = spyDAO.getAutores(1L);
        
        verify(spyDAO).getCollection(1L, "autores");

        assertEquals(autores, result);
    }

    /**
     * Test of getBibliografia method, of class LivroDAO.
     */
    @Test
    public void testGetBibliografia() {
        
        Collection bibliografias = Arrays.asList(new Autor());

        when(spyDAO.getCollection(anyLong(), anyString())).thenReturn(bibliografias);
        
        Collection<?> result = spyDAO.getBibliografia(1L);
        
        verify(spyDAO).getCollection(1L, "bibliografias");

        assertEquals(bibliografias, result);
    }

    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo1() {

        List livros = Arrays.asList(new Livro(), new Livro());
        
        preparaSessionFactoryMock();
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);
        
        Collection<Livro> result = this.dao.buscaLivroPorTitulo("");
        
        assertEquals(livros, result);
    }
    
    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo2() {

        List livros = Arrays.asList(new Livro(), new Livro());
        
        preparaSessionFactoryMock();
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);
        
        Collection<Livro> result = this.dao.buscaLivroPorTitulo(null);
        
        assertEquals(livros, result);
    }
    
    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo3() {

        List livros = Arrays.asList(new Livro(), new Livro());
        
        preparaSessionFactoryMock();
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);
        
        Collection<Livro> result = this.dao.buscaLivroPorTitulo("Livro 2");
        
        assertEquals(livros, result);
    }

    /**
     * Test of obterQuantidadeDeAlunosPorLivro method, of class LivroDAO.
     */
    @Test
    public void testObterQuantidadeDeAlunosPorLivro1() {
        
        this.preparaSessionFactoryMock();
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.createAlias(anyString(), anyString())).thenReturn(criteria);
        when(criteria.setProjection(any(Projection.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(5);
        
        Integer result = this.dao.obterQuantidadeDeAlunosPorLivro(1L);
        
        assertEquals(result.intValue(), 5);
    }
    
    /**
     * Test of obterQuantidadeDeAlunosPorLivro method, of class LivroDAO.
     */
    @Test
    public void testObterQuantidadeDeAlunosPorLivro2() {
        
        this.preparaSessionFactoryMock();
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.createAlias(anyString(), anyString())).thenReturn(criteria);
        when(criteria.setProjection(any(Projection.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(0);
        
        Integer result = this.dao.obterQuantidadeDeAlunosPorLivro(0L);
        
        assertEquals(result.intValue(), 0);
    }

    /**
     * Test of list method, of class LivroDAO.
     */
    @Test
    @Ignore
    public void testList() {
        System.out.println("list");
        LivroDAO instance = new LivroDAO();
        Collection expResult = null;
        Collection result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
