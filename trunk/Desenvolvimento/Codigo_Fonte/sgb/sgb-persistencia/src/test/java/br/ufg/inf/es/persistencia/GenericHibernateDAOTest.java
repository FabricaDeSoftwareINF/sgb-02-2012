package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.model.AbstractEntityModel;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;

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
        
        Session result =  (Session) this.genericDAO.getSession();
        
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
    
    @Test(expected=IllegalArgumentException.class)
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
        
        when((factory.openSession())).thenReturn(session);
        when(session.get(any(genericDAO.getClassEntity().getClass()) , anyLong())).thenReturn(model);
        when(session.close()).thenReturn(connection);
        
        AbstractEntityModel result = genericDAO.find(Long.MIN_VALUE);
       
        assertEquals(model, result);

    }

    /**
     * Test of insert method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testInsert() {
        System.out.println("insert");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Long expResult = null;
        Long result = instance.insert(entidade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testUpdate() {
        System.out.println("update");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.update(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testSave() {
        System.out.println("save");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.save(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testRemove() {
        System.out.println("remove");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.remove(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testRemoveAll() {
        System.out.println("removeAll");
        Collection<Entity> entidades = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        instance.removeAll(entidades);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class GenericHibernateDAO.
     */
    @Test
    @Ignore
    public void testSearch_GenericType() {
        System.out.println("search");
        Entity entidade = null;
        GenericHibernateDAO instance = new GenericHibernateDAOImpl();
        Collection expResult = null;
        Collection result = instance.search(entidade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
