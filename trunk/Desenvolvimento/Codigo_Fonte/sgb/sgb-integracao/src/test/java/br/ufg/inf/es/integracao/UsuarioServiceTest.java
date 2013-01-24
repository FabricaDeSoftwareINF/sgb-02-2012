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
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;
import org.hibernate.type.Type;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;

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
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        UsuarioDAO ususarioDAO = Mockito.mock(UsuarioDAO.class);
        Mockito.when(ususarioDAO.insert(entity)).thenReturn(0L);
        
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
