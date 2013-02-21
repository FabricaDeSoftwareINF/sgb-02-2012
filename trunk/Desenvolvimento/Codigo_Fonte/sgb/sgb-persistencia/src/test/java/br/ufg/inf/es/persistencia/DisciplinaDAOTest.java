/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Disciplina;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.transform.ResultTransformer;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class DisciplinaDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Connection connection;
    private DisciplinaDAO dao;

    @Before
    public void setUp() {

        dao = new DisciplinaDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.connection = mock(Connection.class);
        dao.setSessionFactory(factory);
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
     * Test of getSessionFactory method, of class DisciplinaDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of listarDisciplinasNaoVinculadasACurso method, of class
     * DisciplinaDAO.
     */
    @Test
    public void testListarDisciplinasNaoVinculadasACurso() {

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.setProjection(any(ProjectionList.class))).thenReturn(criteria);
        when(criteria.setResultTransformer(any(ResultTransformer.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.listarDisciplinasNaoVinculadasACurso();

        assertEquals(disciplinas, result);
    }

    /**
     * Test of search method, of class DisciplinaDAO.
     */
    @Test
    public void testSearch1() {

        Disciplina d1 = new Disciplina();
        d1.setId(Long.MIN_VALUE);
        d1.setCodigo("0001");
        d1.setNome("Disciplina 1");

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.search(d1);

        assertEquals(disciplinas, result);
    }

    @Test
    public void testSearch2() {

        Disciplina d1 = new Disciplina();
        d1.setId(Long.MIN_VALUE);
        d1.setNome("Disciplina 1");

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.search(d1);

        assertEquals(disciplinas, result);
    }

    @Test
    public void testSearch3() {

        Disciplina d1 = new Disciplina();
        d1.setCodigo("0001");
        d1.setNome("Disciplina 1");

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.search(d1);

        assertEquals(disciplinas, result);
    }

    @Test
    public void testSearch4() {

        Disciplina d1 = new Disciplina();
        d1.setCodigo("");
        d1.setNome("");

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.search(d1);

        assertEquals(disciplinas, result);
    }

    /**
     * Test of listarDisciplinasDeUmCurso method, of class DisciplinaDAO.
     */
    @Test
    public void testListarDisciplinasDeUmCurso() {

        Long idCurso = 1L;

        List<Disciplina> disciplinas = Arrays.asList(new Disciplina(), new Disciplina());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Disciplina.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(disciplinas);

        Collection<Disciplina> result = this.dao.listarDisciplinasDeUmCurso(idCurso);

        assertEquals(disciplinas, result);
    }
}
