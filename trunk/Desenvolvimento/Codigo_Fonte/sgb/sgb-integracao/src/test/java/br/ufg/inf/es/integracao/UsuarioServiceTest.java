/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import br.ufg.inf.es.persistencia.UsuarioPerfilDAO;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author alunoufg
 */
public class UsuarioServiceTest {

    Usuario entity = new Usuario();
    UsuarioService instance = new UsuarioService();

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
        entity.setSenha("123456");
        entity.setId(Long.MIN_VALUE);
        UsuarioPerfilDAO perfilDao = Mockito.mock(UsuarioPerfilDAO.class);
        Mockito.when(perfilDao.list(0)).thenReturn(new ArrayList<UsuarioPerfil>());
        instance.setUsuarioPerfilDao(perfilDao);

        UsuarioDAO ususarioDAO = Mockito.mock(UsuarioDAO.class);
        instance.setDao(ususarioDAO);
        instance.setUsuarioPerfilDao(perfilDao);
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
    public void testUpdate() throws Exception {

        Usuario entity = new Usuario();
        entity.setSenha("123456");
        entity.setId(Long.MIN_VALUE);

        UsuarioService instance = new UsuarioService();
        UsuarioPerfilDAO perfilDao = Mockito.mock(UsuarioPerfilDAO.class);
        Mockito.when(perfilDao.list(0)).thenReturn(new ArrayList<UsuarioPerfil>());
        instance.setUsuarioPerfilDao(perfilDao);

        UsuarioDAO ususarioDAO = Mockito.mock(UsuarioDAO.class);
        instance.setDao(ususarioDAO);
        instance.setUsuarioPerfilDao(perfilDao);
        instance.update(entity);

    }

    /**
     * Test of remove method, of class UsuarioService.
     */
    @Test
    public void testRemove() throws Exception {
        instance.remove(entity);
    }

    /**
     * Test of removeAll method, of class UsuarioService.
     */
    @Test
    public void testRemoveAll() throws Exception {
       
        Collection<Usuario> collectionEntities = new ArrayList<Usuario>();
      
        instance.removeAll(collectionEntities);
     
    }

    /**
     * Test of search method, of class UsuarioService.
     */
    @Test
   
    public void testSearch() {
        
        
        Collection expResult = new ArrayList<Usuario>();
        Collection result = instance.search(entity);
        assertEquals(expResult, result);
       
    }

   
    @Test
   
    public void testList() {
       
         Collection expResult = new ArrayList<Usuario>();
        Collection result = instance.list();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of refresh method, of class UsuarioService.
     */
    @Test
    public void testRefresh() {
      
        instance.refresh(entity);
        
    }

    /**
     * Test of carreguePerfis method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testCarreguePerfis() {
       
        Usuario usuario = new Usuario();
        Collection expResult = new ArrayList<Usuario>();
        Collection result = instance.carreguePerfis(usuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of authUser method, of class UsuarioService.
     */
    @Test
    @Ignore
    public void testAuthUser() {
        
        String user = "a";
        String password = "123";
        UsuarioService instance = new UsuarioService();
        Usuario expResult = new Usuario();
        expResult.setNome(user);
        expResult.setSenha(password);
        Usuario result = instance.authUser(user, password);
        assertEquals(expResult, result);
       
    }
}
