/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.*;
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
        usuario.setPerfil(UsuarioPerfil.ADM);
        Mockito.when(us.authUser(user, password)).thenReturn(usuario);

        instance.setUsuarioService(us);
        Collection<String> expResult = new ArrayList();
        expResult.add(UsuarioPerfil.ADM.name());
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
        instance.setUsuarioService(us);

        UsuarioService result = instance.getUsuarioService();
        assertEquals(us, result);

    }
}