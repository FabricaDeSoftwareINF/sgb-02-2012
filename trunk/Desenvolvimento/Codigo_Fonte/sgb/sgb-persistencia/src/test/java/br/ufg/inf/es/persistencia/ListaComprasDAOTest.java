/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import sun.security.krb5.internal.crypto.Crc32CksumType;

/**
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class ListaComprasDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private ListaComprasDAO dao;

    @Before
    public void setUp() {
        
        dao = new ListaComprasDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
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
     * Test of findLivrosListaCompras method, of class ListaComprasDAO.
     */
    @Test
    public void testFindLivrosListaCompras() {
        
        preparaSessionFactoryMock();
        
        ListaCompras listaCompras =  new ListaCompras();
        List<ItemListaCompras> listaItens = Arrays.asList(new ItemListaCompras());
        listaCompras.setLivrosDaListaCompras(listaItens);

        when(session.createCriteria(ListaCompras.class)).thenReturn(criteria);
        when(criteria.setFetchMode(anyString(), any(FetchMode.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(listaCompras);
        
        Collection<ItemListaCompras> result = this.dao.findLivrosListaCompras(Long.MIN_VALUE);
        
        assertEquals(listaItens, result);
        
    }
}
