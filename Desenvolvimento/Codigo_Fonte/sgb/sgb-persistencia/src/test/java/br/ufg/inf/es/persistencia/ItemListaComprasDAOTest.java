/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ItemListaCompras;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class ItemListaComprasDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Query query;
    private ItemListaComprasDAO dao;

    @Before
    public void setUp() {

        dao = new ItemListaComprasDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.query = mock(Query.class);
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
     * Test of setSessionFactory method, of class ItemListaComprasDAO.
     */
    @Test
    public void testSetSessionFactory() {
                this.dao.setSessionFactory(this.factory);

        assertEquals(factory, dao.getSessionFactory());

    }

    /**
     * Test of getSessionFactory method, of class ItemListaComprasDAO.
     */
    @Test
    public void testGetSessionFactory() {
               assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of list method, of class ItemListaComprasDAO.
     */
    @Test
    public void testList() {
        
        preparaSessionFactoryMock();
        List<ItemListaCompras> itens = Arrays.asList(new ItemListaCompras(), new ItemListaCompras());    
        
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(itens);
        
        Collection<ItemListaCompras> result = this.dao.list();
    
        assertEquals(itens, result);
    }
}
