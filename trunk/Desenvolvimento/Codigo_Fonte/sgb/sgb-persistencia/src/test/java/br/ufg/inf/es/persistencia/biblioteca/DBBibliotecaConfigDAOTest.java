/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class DBBibliotecaConfigDAOTest {

    private DBBibliotecaConfigDAO dao;
    private SessionFactory factory;
    private Session session;
    private Criteria criteria;

    @Before
    public void setUp() {

        this.dao = new DBBibliotecaConfigDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.dao.setSessionFactory(factory);
    }

    /**
     * Método responsável por definir o retorno do Método openSesison() do mock
     * SessionFactory
     */
    public void preparaSessionFactoryMock() {
        when((factory.openSession())).thenReturn(session);
    }

    /**
     * Test of getSessionFactory method, of class DBBibliotecaConfigDAO.
     */
    @Test
    public void testGetSessionFactory() {

        assertEquals(factory, dao.getSessionFactory());


    }

    /**
     * Test of setSessionFactory method, of class DBBibliotecaConfigDAO.
     */
    @Test
    public void testSetSessionFactory() {

        this.dao.setSessionFactory(this.factory);

        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of getBibliotecaCfg method, of class DBBibliotecaConfigDAO.
     */
    @Test
    public void testGetBibliotecaCfg() {
        
        preparaSessionFactoryMock();
        
        DBBibliotecaConfig dbbConfig =  new DBBibliotecaConfig();
        
        when(session.createCriteria(eq(DBBibliotecaConfig.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(dbbConfig);
        
        DBBibliotecaConfig result = this.dao.getBibliotecaCfg();
        
        assertEquals(dbbConfig, result);

    }
}
