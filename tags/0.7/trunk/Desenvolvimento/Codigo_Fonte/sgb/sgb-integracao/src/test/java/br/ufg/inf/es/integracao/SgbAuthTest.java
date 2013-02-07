/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Alunoinf_2
 */
public class SgbAuthTest {
    
    public SgbAuthTest() {
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
     * Test of login method, of class SgbAuth.
     */
    @Test
    public void testLogin() {
        
        String user = "professor";
        String password = "123";
        SgbAuth instance = new SgbAuth();
        UsuarioService us = Mockito.mock(UsuarioService.class);
        Usuario usuario = new Usuario();
        Mockito.when(us.authUser(user,password)).thenReturn(usuario);
        Collection<Perfil> perfis = new ArrayList();
        perfis.add(new Perfil("ROLE_PROFESSOR"));
        Mockito.when(us.carreguePerfis(usuario)).thenReturn(perfis);
        
        instance.SetUsuariioService(us);
        Collection<String> expResult = new ArrayList();
        expResult.add("ROLE_PROFESSOR");
        Collection result = instance.login(user, password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUsuarioService method, of class SgbAuth.
     */
    @Test
    public void testGetUsuarioService() {
      
        SgbAuth instance = new SgbAuth();
        UsuarioService us = Mockito.mock(UsuarioService.class);
        instance.SetUsuariioService(us);
        
        UsuarioService result = instance.getUsuarioService();
        assertEquals(us, result);
        
    }
}