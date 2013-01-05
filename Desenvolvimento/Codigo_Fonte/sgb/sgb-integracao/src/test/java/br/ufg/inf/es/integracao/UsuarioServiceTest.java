/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import br.ufg.inf.es.persistencia.UsuarioPerfilDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.util.*;
import javax.naming.NamingException;
import javax.naming.Reference;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;
import org.hibernate.type.Type;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author alunoufg
 */
public class UsuarioServiceTest {

    public UsuarioServiceTest() {
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
     * Test of getDAO method, of class UsuarioService.
     */
    @Test
    public void testGetDAO() {

        UsuarioService instance = new UsuarioService();
        UsuarioDAO usuario = new UsuarioDAO();
        instance.setDao(usuario);
        assertEquals(usuario, instance.getDAO());

    }

    /**
     * Test of setDao method, of class UsuarioService.
     */
    @Test
    public void testSetDao() {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioService instance = new UsuarioService();
        instance.setDao(dao);
        assertEquals(dao, instance.getDAO());
    }

    /**
     * Test of getUsuario_perfilDao method, of class UsuarioService.
     */
    @Test
    public void testGetUsuario_perfilDao() {
        UsuarioService instance = new UsuarioService();
        UsuarioPerfilDAO perfil = new UsuarioPerfilDAO();
        instance.setUsuarioPerfilDao(perfil);
        assertEquals(perfil, instance.getUsuarioPerfilDao());
        
    }

    /**
     * Test of setUsuario_perfilDao method, of class UsuarioService.
     */
    @Test
    public void testSetUsuario_perfilDao() {
        UsuarioPerfilDAO usuario_perfilDao = null;
        UsuarioService instance = new UsuarioService();
        instance.setUsuarioPerfilDao(usuario_perfilDao);
    }

    /**
     * Test of insert method, of class UsuarioService.
     */
    @Test
    public void testInsert() throws Exception {
        
        Usuario entity = new Usuario();
        entity.setSenha("123456");
        UsuarioService instance = new UsuarioService();
        UsuarioDAO ususarioDAO = new UsuarioDAO(new SessionFactory() {

            public Session openSession(Connection cnctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Session openSession(Interceptor i) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Session openSession(Connection cnctn, Interceptor i) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Session openSession() throws HibernateException {
                return new Session() {

                    public Object saveOrUpdateCopy(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object saveOrUpdateCopy(Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object saveOrUpdateCopy(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object saveOrUpdateCopy(String string, Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public List find(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public List find(String string, Object o, Type type) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public List find(String string, Object[] os, Type[] types) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Iterator iterate(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Iterator iterate(String string, Object o, Type type) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Iterator iterate(String string, Object[] os, Type[] types) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Collection filter(Object o, String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Collection filter(Object o, String string, Object o1, Type type) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Collection filter(Object o, String string, Object[] os, Type[] types) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public int delete(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public int delete(String string, Object o, Type type) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public int delete(String string, Object[] os, Type[] types) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Query createSQLQuery(String string, String string1, Class type) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Query createSQLQuery(String string, String[] strings, Class[] types) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void save(Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void save(String string, Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void update(Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void update(String string, Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public EntityMode getEntityMode() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public org.hibernate.Session getSession(EntityMode em) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void flush() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void setFlushMode(FlushMode fm) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public FlushMode getFlushMode() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void setCacheMode(CacheMode cm) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public CacheMode getCacheMode() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public SessionFactory getSessionFactory() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Connection connection() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Connection close() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void cancelQuery() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public boolean isOpen() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public boolean isConnected() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public boolean isDirty() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Serializable getIdentifier(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public boolean contains(Object o) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void evict(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object load(Class type, Serializable srlzbl, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object load(String string, Serializable srlzbl, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object load(Class type, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object load(String string, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void load(Object o, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void replicate(Object o, ReplicationMode rm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void replicate(String string, Object o, ReplicationMode rm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Serializable save(Object o) throws HibernateException {
                        return new Long(0);
                    }

                    public Serializable save(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void saveOrUpdate(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void saveOrUpdate(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void update(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void update(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object merge(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object merge(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void persist(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void persist(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void delete(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void delete(String string, Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void lock(Object o, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void lock(String string, Object o, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void refresh(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void refresh(Object o, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public LockMode getCurrentLockMode(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Transaction beginTransaction() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Transaction getTransaction() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Criteria createCriteria(Class type) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Criteria createCriteria(Class type, String string) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Criteria createCriteria(String string) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Criteria createCriteria(String string, String string1) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Query createQuery(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public SQLQuery createSQLQuery(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Query createFilter(Object o, String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Query getNamedQuery(String string) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void clear() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object get(Class type, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object get(Class type, Serializable srlzbl, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object get(String string, Serializable srlzbl) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Object get(String string, Serializable srlzbl, LockMode lm) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public String getEntityName(Object o) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Filter enableFilter(String string) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Filter getEnabledFilter(String string) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void disableFilter(String string) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public SessionStatistics getStatistics() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void setReadOnly(Object o, boolean bln) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public Connection disconnect() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void reconnect() throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void reconnect(Connection cnctn) throws HibernateException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                };
            }

            public Session getCurrentSession() throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public ClassMetadata getClassMetadata(Class type) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public ClassMetadata getClassMetadata(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public CollectionMetadata getCollectionMetadata(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Map getAllClassMetadata() throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Map getAllCollectionMetadata() throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Statistics getStatistics() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            boolean isClosed = false;
            public void close() throws HibernateException {
               isClosed = true;
            }

            public boolean isClosed() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evict(Class type) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evict(Class type, Serializable srlzbl) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictEntity(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictEntity(String string, Serializable srlzbl) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictCollection(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictCollection(String string, Serializable srlzbl) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictQueries() throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void evictQueries(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public StatelessSession openStatelessSession() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public StatelessSession openStatelessSession(Connection cnctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Set getDefinedFilterNames() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public FilterDefinition getFilterDefinition(String string) throws HibernateException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public Reference getReference() throws NamingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        instance.setDao(ususarioDAO);
        Long expResult = new Long(0);
        
        Long result = instance.insert(entity);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of update method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testUpdate() throws Exception {
        System.out.println("update");
        Usuario entity = null;
        UsuarioService instance = new UsuarioService();
        instance.update(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testRemove() throws Exception {
        System.out.println("remove");
        Usuario entity = null;
        UsuarioService instance = new UsuarioService();
        instance.remove(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testRemoveAll() throws Exception {
        System.out.println("removeAll");
        Collection<Usuario> collectionEntities = null;
        UsuarioService instance = new UsuarioService();
        instance.removeAll(collectionEntities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testSearch() {
        System.out.println("search");
        Usuario entity = null;
        UsuarioService instance = new UsuarioService();
        Collection expResult = null;
        Collection result = instance.search(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testList() {
        System.out.println("list");
        UsuarioService instance = new UsuarioService();
        Collection expResult = null;
        Collection result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refresh method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testRefresh() {
        System.out.println("refresh");
        Usuario entity = null;
        UsuarioService instance = new UsuarioService();
        instance.refresh(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carreguePerfis method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testCarreguePerfis() {
        System.out.println("carreguePerfis");
        Usuario usuario = null;
        UsuarioService instance = new UsuarioService();
        Collection expResult = null;
        Collection result = instance.carreguePerfis(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of authUser method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testAuthUser() {
        System.out.println("authUser");
        String user = "";
        String password = "";
        UsuarioService instance = new UsuarioService();
        Usuario expResult = null;
        Usuario result = instance.authUser(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
