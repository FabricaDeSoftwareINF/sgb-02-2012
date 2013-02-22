package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.AbstractEntityModel;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * Classe de Teste da Classe Genérica de Persistência
 *
 * @author Cassio Augusto Silva de Freitas
 */
@RunWith(PowerMockRunner.class)
public class GenericHibernateDAOTest {

    private GenericHibernateDAOImpl genericDAO;
    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Connection connection;
    
    

    @Before
    public void setUp() {

        genericDAO = new GenericHibernateDAOImpl();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.connection = mock(Connection.class);
        genericDAO.setSessionFactory(factory);
    }

    /**
     * Método responsável por definir o retorno do Método openSesison() do mock
     * SessionFactory
     */
    public void preparaSessionFactoryMock() {
        when((factory.openSession())).thenReturn(session);
    }

    /**
     * Test of getSessionFactory method, of class GenericHibernateDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, genericDAO.getSessionFactory());
    }

    /**
     * Test of setSessionFactory method, of class GenericHibernateDAO.
     */
    @Test
    public void testSetSessionFactory() {

        this.genericDAO.setSessionFactory(this.factory);

        assertEquals(factory, genericDAO.getSessionFactory());
    }

    /**
     * Test of getSession method, of class GenericHibernateDAO.
     */
    @Test
    public void testGetSession() {

        when(factory.openSession()).thenReturn(session);

        Session result = (Session) this.genericDAO.getSession();

        assertEquals(session, result);

    }

    /**
     * Test of getSession method, of class GenericHibernateDAO.
     */
    @Test
    public void testGetSession2() {


        when(factory.openSession()).thenReturn(null);

        Session result = (Session) this.genericDAO.getSession();

        assertEquals(null, result);

    }

    /**
     * Test of getClassEntity method, of class GenericHibernateDAO.
     */
    @Test
    public void testGetClassEntity() {

        Class<? extends GenericHibernateDAOImpl> result = genericDAO.getClass();

        assertEquals(GenericHibernateDAOImpl.class, result);
    }

    /**
     * Test of isReferencia method, of class GenericHibernateDAO.
     */
    @Test
    public void testIsReferencia() {

        Object o = new Object();

        genericDAO.isReferencia(o);

        assertEquals(o, o);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsReferencia2() {

        Object o = null;

        genericDAO.isReferencia(o);

    }

    /**
     * Test of find method, of class GenericHibernateDAO.
     */
    @Test
    public void testFind() {

        AbstractEntityModel model = new AbstractEntityModel();
        model.setId(Long.MIN_VALUE);

        preparaSessionFactoryMock();
        when(session.get(Mockito.any(genericDAO.getClassEntity().getClass()), Mockito.anyLong())).thenReturn(model);

        AbstractEntityModel result = genericDAO.find(Long.MIN_VALUE);

        assertEquals(model, result);

    }

    /**
     * Test of insert method, of class GenericHibernateDAO.
     */
    @Test
    public void testInsert() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();
        when(session.save(Mockito.any(AbstractEntityModel.class))).thenReturn(Long.MAX_VALUE);
        Long insert = genericDAO.insert(model);

        assertEquals(Long.MAX_VALUE, insert.longValue());
    }

    /**
     * Test of update method, of class GenericHibernateDAO.
     */
    @Test
    public void testUpdate() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();
        when(session.close()).thenReturn(connection);

        genericDAO.update(model);

        Mockito.verify(session).merge(model);
        Mockito.verify(session).flush();
        Mockito.verify(session).close();
    }

    /**
     * Test of save method, of class GenericHibernateDAO.
     */
    @Test
    public void testSave() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();
        when(session.close()).thenReturn(connection);

        genericDAO.save(model);

        Mockito.verify(session).persist(model);
        Mockito.verify(session).flush();
        Mockito.verify(session).close();
    }

    /**
     * Test of remove method, of class GenericHibernateDAO.
     */
    @Test
    public void testRemove() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();

        genericDAO.remove(model);

        Mockito.verify(session).delete(model);
        Mockito.verify(session).flush();
        Mockito.verify(session).close();
    }

    /**
     * Test of removeAll method, of class GenericHibernateDAO.
     */
    @Test
    public void testRemoveAll() {

        int UMA_INTERACAO = 1;

        int TRES_INTERACAO = 3;

        preparaSessionFactoryMock();
        AbstractEntityModel model1 = new AbstractEntityModel();
        AbstractEntityModel model2 = new AbstractEntityModel();
        Collection collectionToRemove = Arrays.asList(model1, model2);

        genericDAO.removeAll(collectionToRemove);

        Mockito.verify(session, Mockito.atLeast(UMA_INTERACAO)).delete(model1);
        Mockito.verify(session, Mockito.atLeast(UMA_INTERACAO)).delete(model2);
        Mockito.verify(session, Mockito.atLeast(TRES_INTERACAO)).flush();
        Mockito.verify(session, Mockito.atLeast(TRES_INTERACAO)).close();

    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    public void testSearch_GenericType() {

        AbstractEntityModel model = new AbstractEntityModel();
        List<AbstractEntityModel> collection = Arrays.asList(model);

        preparaSessionFactoryMock();
        //when(example.create(any(AbstractEntityModel.class))).thenReturn(example);
        when(session.createCriteria(Mockito.eq(AbstractEntityModel.class))).thenReturn(criteria);
        when(criteria.add(Mockito.any(Example.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(collection);

        Collection<AbstractEntityModel> result = genericDAO.search(model);

        Mockito.verify(session).close();

        assertEquals(Boolean.TRUE, result.contains(model));
    }

    /**
     * Test of createCriteria method, of class GenericHibernateDAO.
     */
    @Test
    public void testCreateCriteria() {

        preparaSessionFactoryMock();
        when(session.createCriteria(Mockito.eq(AbstractEntityModel.class))).thenReturn(criteria);

        Criteria result = genericDAO.createCriteria();

        assertEquals(criteria, result);
    }

    /**
     * Test of getCollection method, of class GenericHibernateDAO.
     */
    @Test
    public void testGetCollection() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();
        
        when(session.get(Mockito.any(genericDAO.getClassEntity().getClass()), Mockito.anyLong())).thenReturn(model);
        
        Collection<?> collection = genericDAO.getCollection(Long.MIN_VALUE, "id");
        
        Mockito.verify(session).get(Mockito.any(genericDAO.getClassEntity().getClass()), Mockito.anyLong());

        assertEquals(null, collection);


    }

    /**
     * Test of list method, of class GenericHibernateDAO.
     */
    @Test
    public void testList() {

        List collection = Arrays.asList(new AbstractEntityModel(), new AbstractEntityModel());

        preparaSessionFactoryMock();
        when(session.createCriteria(Mockito.eq(AbstractEntityModel.class))).thenReturn(criteria);
        when(criteria.addOrder(Mockito.any(Order.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(collection);

        Collection<AbstractEntityModel> result = genericDAO.list();

        assertEquals(collection, result);

    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    public void testSearch_String_StringArr() {

        List collection = Arrays.asList(new AbstractEntityModel(), new AbstractEntityModel());

        preparaSessionFactoryMock();
        when(session.createCriteria(Mockito.eq(AbstractEntityModel.class))).thenReturn(criteria);
        when(criteria.add(Mockito.any(Criterion.class))).thenReturn(criteria);
        when(criteria.addOrder(Mockito.any(Order.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(collection);

        Collection<AbstractEntityModel> result = this.genericDAO.search("key", "Joao", "joaquim", "Joao Joaquim");

        assertEquals(collection, result);

    }
    
     /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    public void testSearch_String_StringArr1() {

        List collection = Arrays.asList(new AbstractEntityModel(), new AbstractEntityModel());

        preparaSessionFactoryMock();
        when(session.createCriteria(Mockito.eq(AbstractEntityModel.class))).thenReturn(criteria);
        when(criteria.add(Mockito.any(Criterion.class))).thenReturn(criteria);
        when(criteria.addOrder(Mockito.any(Order.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(collection);

        Collection<AbstractEntityModel> result = this.genericDAO.search("key", "Joao", "joaquim joaquim", "Joao Joaquim Jose Jurandi");

        assertEquals(collection, result);

    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSearch_String_StringArr_properties_null() {

        genericDAO.search("", null);

    }
    
    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    public void testSearch_String_StringArr_Key_Empty() {
        
        Collection<AbstractEntityModel> search = genericDAO.search("", new String[]{""});

        assertNull(search);
    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSearch_String_StringArr_key_empty() {

        Collection<AbstractEntityModel> search = genericDAO.search("", new String[]{});

        assertNull(search);
    }

    /**
     * Test of refresh method, of class GenericHibernateDAO.
     */
    @Test
    public void testRefresh() {
        
        AbstractEntityModel model = new AbstractEntityModel();
        
        preparaSessionFactoryMock();
        
        this.genericDAO.refresh(model);

        Mockito.verify(session).refresh(model);
        
        Mockito.verify(session).close();
    }

    /**
     * Test of closeSession method, of class GenericHibernateDAO.
     */
    @Test
    public void testCloseSession() {
        
        preparaSessionFactoryMock();
        
        genericDAO.getSession();
        
        when(session.isOpen()).thenReturn(true);
        
        this.genericDAO.closeSession();
        
        Mockito.verify(session).close();
    }

    public class GenericHibernateDAOImpl extends GenericHibernateDAO<AbstractEntityModel> {

        private SessionFactory sessionFactory;

        public SessionFactory getSessionFactory() {
            return this.sessionFactory;
        }

        public void setSessionFactory(SessionFactory sessionFactory) {

            this.sessionFactory = sessionFactory;
        }
    }
}
