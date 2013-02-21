package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.model.AbstractEntityModel;
import br.ufg.inf.es.model.Autor;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.transform.AliasToBeanResultTransformer;

/**
 * Classe de Teste da Classe Genérica de Persistência
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class GenericHibernateDAOTest {

    private GenericHibernateDAOImpl genericDAO;
    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Connection connection;
    private Example example;

    @Before
    public void setUp() {

        genericDAO = new GenericHibernateDAOImpl();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.example = mock(Example.class);
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
        when(session.get(any(genericDAO.getClassEntity().getClass()), anyLong())).thenReturn(model);

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
        when(session.save(any(AbstractEntityModel.class))).thenReturn(Long.MAX_VALUE);
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

        verify(session).merge(model);
        verify(session).flush();
        verify(session).close();
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

        verify(session).persist(model);
        verify(session).flush();
        verify(session).close();
    }

    /**
     * Test of remove method, of class GenericHibernateDAO.
     */
    @Test
    public void testRemove() {

        AbstractEntityModel model = new AbstractEntityModel();

        preparaSessionFactoryMock();

        genericDAO.remove(model);

        verify(session).delete(model);
        verify(session).flush();
        verify(session).close();
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

        verify(session, atLeast(UMA_INTERACAO)).delete(model1);
        verify(session, atLeast(UMA_INTERACAO)).delete(model2);
        verify(session, atLeast(TRES_INTERACAO)).flush();
        verify(session, atLeast(TRES_INTERACAO)).close();

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
        when(session.createCriteria(eq(AbstractEntityModel.class))).thenReturn(criteria);
        when(criteria.add(any(Example.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(collection);
        
        Collection<AbstractEntityModel> result = genericDAO.search(model);
        
        verify(session).close();
        
        assertEquals(Boolean.TRUE, result.contains(model));
    }

    /**
     * Test of createCriteria method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testCreateCriteria() {
        System.out.println("createCriteria");
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Criteria expResult = null;
        Criteria result = instance.createCriteria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCollection method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetCollection() {
        System.out.println("getCollection");
        Long id = null;
        String property = "";
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Collection expResult = null;
        Collection result = instance.getCollection(id, property);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testList() {
        System.out.println("list");
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Collection expResult = null;
        Collection result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testSearch_String_StringArr() {
        System.out.println("search");
        String key = "";
        String[] properties = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Collection expResult = null;
        Collection result = instance.search(key, properties);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refresh method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testRefresh() {
        System.out.println("refresh");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.refresh(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeSession method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testCloseSession() {
        System.out.println("closeSession");
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.closeSession();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalize method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testFinalize() throws Throwable {
        System.out.println("finalize");
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.finalize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
